package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.post.model.ResolvedPost


interface PostReadUseCase {
    fun getById(id: Long): ResolvedPost?
}