package ms2709.kafka.post_search_usecase

import ms2709.kafka.post.model.ResolvedPost

interface PostSearchUseCase {
    fun getSearchResultByKeyword(keyword: String, pageIndex: Int): List<ResolvedPost>
}