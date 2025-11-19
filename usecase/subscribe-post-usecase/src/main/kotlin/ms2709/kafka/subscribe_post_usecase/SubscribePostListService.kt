package ms2709.kafka.subscribe_post_usecase

import ms2709.kafka.usecase.core.port.subscribe.SubscribingPostPort
import ms2709.kafka.usecase.post_resolving_help_usecase.PostResolvingHelpUseCase
import org.springframework.stereotype.Component
import ms2709.kafka.post.model.ResolvedPost

@Component
class SubscribePostListService (
    private val subscribingPostPort: SubscribingPostPort,
    private val postResolvingHelpUseCase: PostResolvingHelpUseCase
): SubscribePostListUseCase{
    private  val PAGE_SIZE = 5

    override fun listSubscribingInboxPosts(request: SubscribePostListUseCase.Request): List<ResolvedPost> {
        require(request.followerUserId != null) { "Follower userId must not be null" }
        return subscribingPostPort.listPostIdsByFollowerUserIdWithPagination(request.followerUserId!!, request.pageIndex, PAGE_SIZE).run {
            postResolvingHelpUseCase.resolvePostsByIds(this)
        }
    }
}