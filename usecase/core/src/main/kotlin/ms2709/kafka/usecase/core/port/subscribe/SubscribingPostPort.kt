package ms2709.kafka.usecase.core.port.subscribe

import ms2709.kafka.domain.post.model.Post

interface SubscribingPostPort {
    fun addPostToFollowerInboxes(post: Post, followerUserIds: List<Long>)
    fun removePostFromFollowerInboxes(postId: Long)
    fun listPostIdsByFollowerUserIdWithPagination(followerUserId: Long, pageIndex: Int, pageSize: Int): List<Long>
}
