package ms2709.kafka.mongodb.subscribe

interface SubscribingPostCustomRepository {
    fun findByFollowerUserIdWithPagination(
        followerUserId: Long,
        pageIndex: Int,
        pageSize: Int
    ): List<SubscribingPostDocument>

    fun deleteAllByPostId(postId: Long)
}