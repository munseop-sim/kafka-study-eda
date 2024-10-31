package ms2709.kafka.post_search_usecase

import ms2709.kafka.inspectedpost.model.InspectedPost

interface PostIndexingUseCase {
    fun save(post: InspectedPost)
    fun delete(postId: Long)
}