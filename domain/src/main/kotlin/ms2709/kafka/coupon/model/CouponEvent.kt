package ms2709.kafka.coupon.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

class CouponEvent {
    var id: Long? = null // coupon event의 id
    var displayName: String? = null // coupon에 대한 노출 이름
    var expiresAt: LocalDateTime? = null // coupon 만료 일시
    var issueLimit: Long? = null // coupon 발급 제한 수

    val isExpired: Boolean
        @JsonIgnore
        get() {
            expiresAt ?: return false
            return expiresAt!!.isBefore(LocalDateTime.now())
        }

    companion object {
        fun generate(
            displayName: String,
            expiresAt: LocalDateTime,
            issueLimit: Long,
        ):CouponEvent {
            return CouponEvent().apply {
                this.displayName = displayName
                this.expiresAt = expiresAt
                this.issueLimit = issueLimit
            }
        }


        fun generate(
            id:Long,
            displayName: String,
            expiresAt: LocalDateTime,
            issueLimit: Long,
        ):CouponEvent {
            return CouponEvent().apply {
                this.id = id
                this.displayName = displayName
                this.expiresAt = expiresAt
                this.issueLimit = issueLimit
            }
        }
    }
}