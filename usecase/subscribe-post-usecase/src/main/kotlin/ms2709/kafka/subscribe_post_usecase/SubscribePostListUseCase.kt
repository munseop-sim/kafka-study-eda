package ms2709.kafka.subscribe_post_usecase

import ms2709.kafka.domain.post.model.ResolvedPost

interface SubscribePostListUseCase {
    fun listSubscribingInboxPosts(request: Request): List<ResolvedPost>

    data class Request (
        var pageIndex:Int = 0,
        var followerUserId:Long? = null
    )
}