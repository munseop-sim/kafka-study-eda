package ms2709.kafka.subscribe_post_usecase

import ms2709.kafka.usecase.core.port.subscribe.SubscribingPostPort
import org.springframework.stereotype.Component

@Component
class SubscribePostRemoveFromInboxService(
    private val subscribingPostPort: SubscribingPostPort,
): SubscribePostRemoveFromInboxUseCase {
    override fun deleteSubscribingInboxPost(postId: Long) {
        subscribingPostPort.removePostFromFollowerInboxes(postId)
    }
}