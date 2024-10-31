package ms2709.kafka.coupon.model

import java.time.LocalDateTime

class Coupon {
    var id: Long? = null // coupon id (동일한 coupon event라 해도, 발급받은 유저마다 coupon id가 다 다름)
    var userId: Long? = null // coupon을 발급받은 user의 id
    var couponEventId: Long? = null // coupon event의 id
    var issuedAt: LocalDateTime? = null // coupon 발급 일시
    var usedAt: LocalDateTime? = null // coupon 사용 일시

    fun use(): Coupon {
        this.usedAt = LocalDateTime.now()
        return this
    }

    companion object{
        fun generate(
            userId:Long,
            couponEventId: Long,
        ):Coupon{
            return Coupon().apply {
                this.userId = userId
                this.couponEventId = couponEventId
                this.issuedAt = LocalDateTime.now()
            }
        }
    }

}