package ms2709.kafka.coupon_worker

import ms2709.kafka.adapter.kafka.common.Topic
import ms2709.kafka.adapter.kafka.coupon.CouponIssueRequestMessage
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate
import ms2709.kafka.coupon_usecase.IssueCouponUseCase
import ms2709.kafka.usecase.core.port.coupon.CouponIssueRequestPort
import ms2709.kafka.usecase.core.port.coupon.CouponPort
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class CouponIssueWorker(
    private val issueCouponUseCase: IssueCouponUseCase
) {
    private val objectMapper = CustomObjectMapper()
    private val log by LogDelegate()

    @KafkaListener(
    topics = [ Topic.COUPON_ISSUE_REQUEST ],
    groupId = "coupon-issue-request",
    concurrency = "3"
    )
    fun listen(consumedMessage:ConsumerRecord<String,String>, acknowledgment: Acknowledgment) {
        val message = consumedMessage.value().run {
            objectMapper.readValue(this, CouponIssueRequestMessage::class.java)
        }

        issueCouponUseCase.save(couponEventId =  message.couponEventId!!, userId =  message.userId!!)
        log.info("{} message consume success (key:{})", Topic.COUPON_ISSUE_REQUEST, consumedMessage.key())
    }
}
