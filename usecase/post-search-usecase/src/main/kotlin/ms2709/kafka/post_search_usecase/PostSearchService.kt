package ms2709.kafka.post_search_usecase

import ms2709.kafka.usecase.core.port.post.PostSearchPort
import ms2709.kafka.usecase.post_resolving_help_usecase.PostResolvingHelpUseCase
import org.springframework.stereotype.Service
import post.model.ResolvedPost

@Service
class PostSearchService (
    private val postSearchPort: PostSearchPort,
    private val postResolvingHelpUseCase: PostResolvingHelpUseCase
): PostSearchUseCase {
    private  val  PAGE_SIZE = 10
    override fun getSearchResultByKeyword(keyword: String, pageIndex: Int): List<ResolvedPost> {
        return postSearchPort.searchPostIdsByKeyword(keyword, pageIndex, PAGE_SIZE).run {
            postResolvingHelpUseCase.resolvePostsByIds(this)
        }
    }
}