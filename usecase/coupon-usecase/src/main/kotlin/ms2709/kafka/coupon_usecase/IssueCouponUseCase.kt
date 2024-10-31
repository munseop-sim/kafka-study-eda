package ms2709.kafka.coupon_usecase

import ms2709.kafka.coupon.model.Coupon

interface IssueCouponUseCase {
    fun save(couponEventId: Long, userId: Long): Coupon
}