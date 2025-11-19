package ms2709.kafka.mongodb.subscribe

import ms2709.kafka.common.LogDelegate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
open class SubscribingPostCustomRepositoryImpl (
    private val mongoTemplate: MongoTemplate
): SubscribingPostCustomRepository {
    private val log by LogDelegate()
    override fun findByFollowerUserIdWithPagination(
        followerUserId: Long,
        pageIndex: Int,
        pageSize: Int
    ): List<SubscribingPostDocument> {
        val query = Query()
            .addCriteria(Criteria.where("followerUserId").`is`(followerUserId))
            .with(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    Sort.by(Sort.Direction.DESC, "postCreatedAt")
                )
            )
        log.debug("MongoDB query: {}", query)
        return mongoTemplate.find(query, SubscribingPostDocument::class.java, "subscribingInboxPosts")
    }

    override fun deleteAllByPostId(postId: Long) {
        val query = Query()
        query.addCriteria(Criteria.where("postId").`is`(postId))
        mongoTemplate.remove(query, SubscribingPostDocument::class.java)
    }
}