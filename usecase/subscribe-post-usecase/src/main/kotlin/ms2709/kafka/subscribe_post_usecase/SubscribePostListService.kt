package ms2709.kafka.subscribe_post_usecase
import ms2709.kafka.domain.post.model.ResolvedPost
import ms2709.kafka.usecase.core.port.subscribe.SubscribingPostPort
import ms2709.kafka.usecase.post_resolving_help_usecase.PostResolvingHelpUseCase
import org.springframework.stereotype.Component

@Component
class SubscribePostListService (
    private val subscribingPostPort: SubscribingPostPort,
    private val postResolvingHelpUseCase: PostResolvingHelpUseCase
): SubscribePostListUseCase{
    private  val PAGE_SIZE = 5;

    override fun listSubscribingInboxPosts(request: SubscribePostListUseCase.Request): List<ResolvedPost> {
        assert(request.followerUserId != null)
        return subscribingPostPort.listPostIdsByFollowerUserIdWithPagination(request.followerUserId!!, request.pageIndex, PAGE_SIZE).run {
            postResolvingHelpUseCase.resolvePostsByIds(this)
        }
    }
}