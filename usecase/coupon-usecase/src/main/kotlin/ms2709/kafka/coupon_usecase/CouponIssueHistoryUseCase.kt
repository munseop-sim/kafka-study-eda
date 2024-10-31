package ms2709.kafka.coupon_usecase

interface CouponIssueHistoryUseCase {
    fun isFirstRequestFromUser(couponEventId: Long, userId: Long): Boolean
    fun hasRemainingCoupon(couponEventId: Long): Boolean
}