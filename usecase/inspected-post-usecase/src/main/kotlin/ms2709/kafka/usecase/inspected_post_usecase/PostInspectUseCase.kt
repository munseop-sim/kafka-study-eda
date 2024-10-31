package ms2709.kafka.usecase.inspected_post_usecase

import ms2709.kafka.inspectedpost.model.InspectedPost
import ms2709.kafka.post.model.Post


interface PostInspectUseCase {
    fun inspectAndGetIfValid(post: Post): InspectedPost?
}