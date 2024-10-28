package ms2709.kafka.usecase.post_usecase

import post.model.ResolvedPost


interface PostReadUseCase {
    fun getById(id: Long): ResolvedPost?
}