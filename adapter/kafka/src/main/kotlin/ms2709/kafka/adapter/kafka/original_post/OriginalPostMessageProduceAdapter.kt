package ms2709.kafka.adapter.kafka.original_post

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.common.CustomObjectMapper

import ms2709.kafka.usecase.core.port.post.OriginalPostMessageProducePort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import post.model.Post

@Component
class OriginalPostMessageProduceAdapter(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : OriginalPostMessageProducePort {

    private val objectMapper = CustomObjectMapper()

    override fun sendCreateMessage(post: Post) {
        post.toMessage(post.id!!, OperationTypes.CREATE).run {
            this@OriginalPostMessageProduceAdapter.sendMessage(this)
        }
    }

    override fun sendUpdateMessage(post: Post) {
        post.toMessage(post.id!!, OperationTypes.UPDATE).run {
            this@OriginalPostMessageProduceAdapter.sendMessage(this)
        }
    }

    override fun sendDeleteMessage(id: Long) {
        OriginalPostMessage().apply {
            this.id = id
            payLoad = null
            operationType = OperationTypes.DELETE
        }.run {
            this@OriginalPostMessageProduceAdapter.sendMessage(this)
        }
    }


    private fun sendMessage(message: OriginalPostMessage) {
        kafkaTemplate.send(Topic.ORIGINAL_TOPIC, message.id!!.toString(),objectMapper.writeValueAsString(message))
    }
}