package ms2709.kafka.coupon_usecase

import ms2709.kafka.coupon.model.CouponEvent
import ms2709.kafka.usecase.core.port.coupon.CouponEventCachePort
import ms2709.kafka.usecase.core.port.coupon.CouponIssueRequestHistoryPort
import ms2709.kafka.usecase.core.port.coupon.CouponPort
import org.springframework.stereotype.Component

@Component
open class CouponIssueHistoryService(
    private val couponIssueRequestHistoryPort: CouponIssueRequestHistoryPort,
    private val couponEventPort: CouponPort,
    private val couponEventCachePort: CouponEventCachePort
) : CouponIssueHistoryUseCase {
    override fun isFirstRequestFromUser(couponEventId: Long, userId: Long): Boolean {
        return couponIssueRequestHistoryPort.setHistoryIfNotExists(couponEventId, userId)
    }

    override fun hasRemainingCoupon(couponEventId: Long): Boolean {
        val couponEvent = getCouponEventById(couponEventId)
        return couponIssueRequestHistoryPort.getRequestSequentialNumber(couponEventId) <= (couponEvent.issueLimit ?: 0)
    }


    private fun getCouponEventById(couponEventId: Long): CouponEvent {
        val couponEventCache: CouponEvent? = couponEventCachePort.get(couponEventId)
        if (couponEventCache != null) {
            return couponEventCache
        } else {
            val couponEvent: CouponEvent = couponEventPort.findCouponEventById(couponEventId)
                ?: throw RuntimeException("Coupon event does not exist.")
            couponEventCachePort.set(couponEvent)
            return couponEvent
        }
    }
}