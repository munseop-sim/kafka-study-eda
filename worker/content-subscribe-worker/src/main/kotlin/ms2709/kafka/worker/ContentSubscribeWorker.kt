package ms2709.kafka.worker

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.adapter.kafka.inspected_post.InspectedPostMessage
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate
import ms2709.kafka.subscribe_post_usecase.SubscribePostAddToInboxService
import ms2709.kafka.subscribe_post_usecase.SubscribePostRemoveFromInboxUseCase
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class ContentSubscribeWorker (
    private val subscribePostAddToInboxService: SubscribePostAddToInboxService,
    private val subscribePostRemoveFromInboxUseCase: SubscribePostRemoveFromInboxUseCase
){

    private val objectMapper = CustomObjectMapper()
    private val log by LogDelegate()

    @KafkaListener(
        topics = [Topic.INSPECTED_POST],
        groupId = "subscribing-post-consumer-group",
        concurrency = "3"
    )
    fun listen(message: ConsumerRecord<String, String>, acknowledgment: Acknowledgment) {
        val inspectedPostMessage: InspectedPostMessage =
            objectMapper.readValue(message.value(), InspectedPostMessage::class.java)

        when(inspectedPostMessage.operationType){
            OperationTypes.CREATE ->{
                handleCreate(inspectedPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.DELETE ->{
                handleDelete(inspectedPostMessage)
                acknowledgment.acknowledge()
            }
            OperationTypes.UPDATE ->{
                log.info("업데이트는 아무것도 하지 않음")
                acknowledgment.acknowledge()
            }
            else-> throw IllegalArgumentException("Unknown operation type")
        }
    }

    private fun handleCreate(inspectedPostMessage: InspectedPostMessage) {
        subscribePostAddToInboxService.saveSubscribingInboxPost(inspectedPostMessage.payLoad!!.post!!)
    }

    private fun handleDelete(inspectedPostMessage: InspectedPostMessage) {
        subscribePostRemoveFromInboxUseCase.deleteSubscribingInboxPost(inspectedPostMessage.id!!)
    }
}