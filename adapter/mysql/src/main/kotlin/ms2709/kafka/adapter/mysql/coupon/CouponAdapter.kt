package ms2709.kafka.adapter.mysql.coupon

import ms2709.kafka.coupon.model.Coupon
import ms2709.kafka.coupon.model.CouponEvent
import ms2709.kafka.coupon.model.ResolvedCoupon
import ms2709.kafka.usecase.core.port.coupon.CouponPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class CouponAdapter(
    private val couponEntityRepository: CouponEntityRepository,
    private val couponEventEntityRepository: CouponEventEntityRepository
): CouponPort {
    override fun findCouponEventById(id: Long): CouponEvent? {
        return couponEventEntityRepository.findByIdOrNull(id)?.let {
            it.toModel()
        }
    }

    override fun saveCoupon(coupon: Coupon): Coupon {
        return couponEntityRepository.save(coupon.toEntity()).toModel()
    }

    override fun listCouponByUserId(userId: Long): List<ResolvedCoupon> {
        return couponEntityRepository.findAllByUserId(userId).map {
            it.toResolvedCoupon()
        }
    }
}