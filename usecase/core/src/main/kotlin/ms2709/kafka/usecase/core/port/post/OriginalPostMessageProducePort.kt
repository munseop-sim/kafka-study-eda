package ms2709.kafka.usecase.core.port.post

import ms2709.kafka.domain.post.model.Post

interface OriginalPostMessageProducePort {
    fun sendCreateMessage(post: Post)
    fun sendUpdateMessage(post: Post)
    fun sendDeleteMessage(id: Long)
}