package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post


interface PostCreateUseCase {
    fun create(request:Request): Post
    data class Request(
        val userId:Long,
        val title: String,
        val content: String,
        val categoryId:Long
    )
}