package ms2709.kafka.coupon_usecase

import ms2709.kafka.usecase.core.port.coupon.CouponIssueRequestPort
import org.springframework.stereotype.Service

@Service
open class RequestCouponIssueService (
    private val couponIssueRequestPort: CouponIssueRequestPort
): RequestCouponIssueUseCase {
    override fun queue(couponEventId: Long, userId: Long) {
        couponIssueRequestPort.sendMessage(userId = userId, couponEventId =  couponEventId)
    }

}