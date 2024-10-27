package ms2709.kafka.subscribe_post_usecase

interface SubscribePostRemoveFromInboxUseCase {
    fun deleteSubscribingInboxPost(postId: Long)
}