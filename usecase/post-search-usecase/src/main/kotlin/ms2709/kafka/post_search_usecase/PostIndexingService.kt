package ms2709.kafka.post_search_usecase

import ms2709.kafka.inspectedpost.model.InspectedPost
import ms2709.kafka.usecase.core.port.post.PostSearchPort
import org.springframework.stereotype.Service

@Service
open class PostIndexingService (
    private val postSearchPort: PostSearchPort
): PostIndexingUseCase{
    override fun save(post: InspectedPost) {
        postSearchPort.indexPost(post)
    }

    override fun delete(postId: Long) {
        postSearchPort.deletePost(postId)
    }
}