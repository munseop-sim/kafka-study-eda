package ms2709.kafka.coupon.model

class ResolvedCoupon {
    lateinit var coupon: Coupon
    lateinit var couponEvent: CouponEvent

    val canBeUsed:Boolean
        get() {
            return !couponEvent.isExpired && coupon.usedAt == null
        }

    companion object {
        fun generate(coupon: Coupon,
                     couponEvent: CouponEvent
        ): ResolvedCoupon {
            return ResolvedCoupon().apply {
                this.coupon = coupon
                this.couponEvent = couponEvent
            }
        }
    }
}