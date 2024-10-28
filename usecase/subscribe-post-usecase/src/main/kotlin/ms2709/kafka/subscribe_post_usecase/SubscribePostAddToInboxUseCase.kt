package ms2709.kafka.subscribe_post_usecase

import post.model.Post


interface  SubscribePostAddToInboxUseCase {
    fun saveSubscribingInboxPost(post: Post)
}