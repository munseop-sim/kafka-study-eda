package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.post.model.Post


interface PostUpdateUseCase {
    fun update(request:Request): Post?

    data class Request(
        val postId:Long,
        val title:String,
        val content: String,
        val categoryId:Long
    )
}