package ms2709.kafka.coupon_usecase

import ms2709.kafka.coupon.model.ResolvedCoupon
import ms2709.kafka.usecase.core.port.coupon.CouponPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class ListUsableCouponsService(
    private val couponPort: CouponPort
) : ListUsableCouponsUseCase {
    override fun listByUserId(userId: Long): List<ResolvedCoupon> {
        return couponPort.listCouponByUserId(userId = userId).filter { it.canBeUsed }
    }
}