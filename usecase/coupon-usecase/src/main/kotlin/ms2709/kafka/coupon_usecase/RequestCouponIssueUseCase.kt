package ms2709.kafka.coupon_usecase

interface RequestCouponIssueUseCase {
    fun queue(couponEventId: Long, userId: Long)
}