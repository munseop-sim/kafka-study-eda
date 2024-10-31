package ms2709.kafka.usecase.core.port.coupon

import ms2709.kafka.coupon.model.CouponEvent

interface CouponEventCachePort {
    fun set(couponEvent: CouponEvent)
    fun get(couponEventId: Long): CouponEvent?
}