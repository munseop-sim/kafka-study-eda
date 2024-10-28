package ms2709.kafka.usecase.post_usecase

import post.model.Post


interface PostDeleteUseCase {

    fun delete(request:Request) : Post?

    data class Request(
        val postId:Long
    )

}