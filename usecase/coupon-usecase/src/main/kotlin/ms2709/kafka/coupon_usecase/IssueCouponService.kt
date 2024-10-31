package ms2709.kafka.coupon_usecase

import ms2709.kafka.coupon.model.Coupon
import ms2709.kafka.usecase.core.port.coupon.CouponPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
open class IssueCouponService(
    private val couponPort: CouponPort
): IssueCouponUseCase {


    override fun save(couponEventId: Long, userId: Long): Coupon {
        return couponPort.saveCoupon(
            Coupon.generate(
                userId = userId,
                couponEventId = couponEventId
            )
        )
    }
}