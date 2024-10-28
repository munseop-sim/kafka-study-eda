package ms2709.kafka.subscribe_post_usecase


import ms2709.kafka.usecase.core.port.metadata.MetadataPort
import ms2709.kafka.usecase.core.port.subscribe.SubscribingPostPort
import org.springframework.stereotype.Component
import post.model.Post

@Component
class SubscribePostAddToInboxService (
    private val subscribingPostPort: SubscribingPostPort,
    private val metadataPort: MetadataPort
): SubscribePostAddToInboxUseCase {
    override fun saveSubscribingInboxPost(post: Post) {
        metadataPort.listFollowerIdsByUserId(post.userId!!).run {
            subscribingPostPort.addPostToFollowerInboxes(post, this)
        }
    }
}