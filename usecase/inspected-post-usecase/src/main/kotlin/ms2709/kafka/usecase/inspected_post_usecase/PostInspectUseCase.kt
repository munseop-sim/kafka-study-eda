package ms2709.kafka.usecase.inspected_post_usecase

import inspectedpost.model.InspectedPost
import post.model.Post


interface PostInspectUseCase {
    fun inspectAndGetIfValid(post: Post): InspectedPost?
}