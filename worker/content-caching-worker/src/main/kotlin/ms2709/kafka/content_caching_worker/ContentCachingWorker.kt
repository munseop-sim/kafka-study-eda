package ms2709.kafka.content_caching_worker

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.adapter.kafka.original_post.OriginalPostMessage
import ms2709.kafka.adapter.kafka.original_post.toModel
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.usecase.post_resolving_help_usecase.PostResolvingHelpUseCase
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class ContentCachingWorker (
    private val postResolvingHelpUseCase: PostResolvingHelpUseCase
){
    private val objectMapper= CustomObjectMapper()


    @KafkaListener(topics = [Topic.ORIGINAL_TOPIC], groupId = "cache-post-consumer-group", concurrency = "3")
    fun listen(message: ConsumerRecord<String, String>, acknowledgment: Acknowledgment){
        val originalPostMessage = objectMapper.readValue(message.value(), OriginalPostMessage::class.java)
        when(originalPostMessage.operationType){
            OperationTypes.CREATE ->{
                handleCreateMessage(originalPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.UPDATE ->{
                handleUpdateMessage(originalPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.DELETE ->{
                handleDeleteMessage(originalPostMessage)
                acknowledgment.acknowledge()

            }
            else-> throw IllegalArgumentException("Unknown operation type")
        }
    }

    private fun handleDeleteMessage(originalPostMessage: OriginalPostMessage) {
        postResolvingHelpUseCase.deleteResolvedPost(originalPostMessage.id!!)
    }

    private fun handleUpdateMessage(originalPostMessage: OriginalPostMessage) {
        postResolvingHelpUseCase.resolvePostAndSave(originalPostMessage.toModel())
    }

    private fun handleCreateMessage(originalPostMessage: OriginalPostMessage) {
        postResolvingHelpUseCase.resolvePostAndSave(originalPostMessage.toModel())
    }
}