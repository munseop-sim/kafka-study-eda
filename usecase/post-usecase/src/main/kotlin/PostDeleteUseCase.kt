package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post

interface PostDeleteUseCase {

    fun delete(request:Request) : Post?

    data class Request(
        val postId:Long
    )

}