package ms2709.kafka.subscribe_post_usecase

import ms2709.kafka.domain.post.model.Post

interface  SubscribePostAddToInboxUseCase {
    fun saveSubscribingInboxPost(post: Post)
}