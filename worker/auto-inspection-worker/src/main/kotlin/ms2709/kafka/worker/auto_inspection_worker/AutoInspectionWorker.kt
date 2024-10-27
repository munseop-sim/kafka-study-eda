package ms2709.kafka.worker.auto_inspection_worker

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.adapter.kafka.original_post.OriginalPostMessage
import ms2709.kafka.adapter.kafka.original_post.toModel
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate
import ms2709.kafka.usecase.core.port.inspectedpost.InspectedPostMessageProducePort

import ms2709.kafka.usecase.inspected_post_usecase.PostInspectUseCase
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
open class AutoInspectionWorker (
    private val postInspectUseCase: PostInspectUseCase,
    private val inspectedPostMessageProducePort: InspectedPostMessageProducePort
){
    private val log by LogDelegate()
    private val objectMapper = CustomObjectMapper()

    @KafkaListener(
        topics = [Topic.ORIGINAL_TOPIC ],
        groupId = "auto-inspection-consumer-group",
        concurrency = "3"
    )
    fun listen(message:ConsumerRecord<String,String>, acknowledgment: Acknowledgment){
        log.info("listen message -> {}", message.value())
        val originalPostMessage = objectMapper.readValue(message.value(), OriginalPostMessage::class.java)
        when(originalPostMessage.operationType){
            OperationTypes.CREATE -> {
                handleCreate(originalPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.UPDATE -> {
                handleUpdate(originalPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.DELETE -> {
                handleDelete(originalPostMessage)
                acknowledgment.acknowledge()
            }
            else-> throw IllegalArgumentException("Unknown operation type")

        }
    }

    private fun handleDelete(originalPostMessage: OriginalPostMessage) {
        inspectedPostMessageProducePort.sendDeleteMessage(originalPostMessage.id!!)
    }

    private fun handleUpdate(originalPostMessage: OriginalPostMessage) {
        postInspectUseCase.inspectAndGetIfValid(originalPostMessage.toModel())?.let {
            inspectedPostMessageProducePort.sendUpdateMessage(it)
        }
    }

    private fun handleCreate(originalPostMessage: OriginalPostMessage) {
        postInspectUseCase.inspectAndGetIfValid(originalPostMessage.toModel())?.let {
            inspectedPostMessageProducePort.sendCreateMessage(it)
        }
    }

}