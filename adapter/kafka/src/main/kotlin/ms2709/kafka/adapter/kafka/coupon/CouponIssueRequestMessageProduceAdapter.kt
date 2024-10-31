package ms2709.kafka.adapter.kafka.coupon

import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate
import ms2709.kafka.usecase.core.port.coupon.CouponIssueRequestPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponIssueRequestMessageProduceAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
): CouponIssueRequestPort {
    private val log by LogDelegate()
    private val objectMapper = CustomObjectMapper()

    override fun sendMessage(userId: Long, couponEventId: Long) {
        CouponIssueRequestMessage().apply {
            this.userId = userId
            this.couponEventId = couponEventId
        }.run {
            kafkaTemplate.send(Topic.COUPON_ISSUE_REQUEST, userId.toString(), objectMapper.writeValueAsString(this))
            log.info("{} message send success (key:{})", Topic.COUPON_ISSUE_REQUEST, userId)
        }
    }
}