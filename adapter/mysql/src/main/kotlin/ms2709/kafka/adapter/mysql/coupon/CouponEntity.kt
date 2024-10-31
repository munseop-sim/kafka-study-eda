package ms2709.kafka.adapter.mysql.coupon

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Table(name="coupon")
@EntityListeners(AuditingEntityListener::class)
@DynamicUpdate
@Entity
open class CouponEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null,

    @Column(name="user_id")
    private var userId: Int? = null,

    @Column(name = "coupon_event_id")
    private var couponEventId: Int? = null,

    @Column(name="issued_at")
    private var issuedAt: LocalDateTime? = null,

    @Column(name="used_at")
    private var usedAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_event_id", nullable = false, insertable = false, updatable = false)
    private var couponEvent: CouponEventEntity? = null,

    @Column(name="created_at")
    @CreatedDate
    private var createdAt:LocalDateTime? = null
){

    open fun getId(): Int? {
        return id
    }

    open fun getUserId(): Int? {
        return userId
    }

    open fun getCouponEventId(): Int? {
        return couponEventId
    }

    open fun getIssuedAt(): LocalDateTime? {
        return issuedAt
    }

    open fun getUsedAt(): LocalDateTime? {
        return usedAt
    }

    open fun getCouponEvent(): CouponEventEntity? {
        return couponEvent
    }

    open fun getCreatedAt(): LocalDateTime? {
        return createdAt
    }


}