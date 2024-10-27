package ms2709.kafka.mongodb.subscribe

import ms2709.kafka.domain.post.model.Post
import ms2709.kafka.usecase.core.port.subscribe.SubscribingPostPort
import org.springframework.stereotype.Component

@Component
class SubscribingPostAdapter (
    private val subscribingPostRepository: SubscribingPostRepository
): SubscribingPostPort {
    override fun addPostToFollowerInboxes(post: Post, followerUserIds: List<Long>) {
        followerUserIds.map { followerUserId ->
            SubscribingPostDocument.generate(post, followerUserId)
        }.run {
            subscribingPostRepository.saveAll(this)
        }
    }

    override fun removePostFromFollowerInboxes(postId: Long) {
        subscribingPostRepository.deleteAllByPostId(postId)
    }

    override fun listPostIdsByFollowerUserIdWithPagination(
        followerUserId: Long,
        pageIndex: Int,
        pageSize: Int
    ): List<Long> {
        return subscribingPostRepository.findByFollowerUserIdWithPagination(
            followerUserId,
            pageIndex,
            pageSize
        ).mapNotNull { it.postId }
    }
}