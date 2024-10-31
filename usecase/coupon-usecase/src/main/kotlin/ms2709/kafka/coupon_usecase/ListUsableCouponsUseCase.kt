package ms2709.kafka.coupon_usecase

import ms2709.kafka.coupon.model.ResolvedCoupon

interface ListUsableCouponsUseCase {
    fun listByUserId(userId: Long): List<ResolvedCoupon>
}