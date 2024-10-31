package ms2709.kafka.usecase.core.port.coupon

import ms2709.kafka.coupon.model.Coupon
import ms2709.kafka.coupon.model.CouponEvent
import ms2709.kafka.coupon.model.ResolvedCoupon

interface CouponPort {
    fun findCouponEventById(id: Long): CouponEvent?
    fun saveCoupon(coupon: Coupon): Coupon
    fun listCouponByUserId(userId: Long): List<ResolvedCoupon>
}