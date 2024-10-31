package ms2709.kafka.usecase.core.port.coupon

interface CouponIssueRequestPort {
    fun sendMessage(userId: Long, couponEventId: Long)
}