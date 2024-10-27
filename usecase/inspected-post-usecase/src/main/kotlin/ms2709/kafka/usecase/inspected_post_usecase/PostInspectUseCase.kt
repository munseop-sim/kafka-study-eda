package ms2709.kafka.usecase.inspected_post_usecase

import inspectedpost.model.InspectedPost
import ms2709.kafka.domain.post.model.Post

interface PostInspectUseCase {
    fun inspectAndGetIfValid(post: Post): InspectedPost?
}