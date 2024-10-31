package ms2709.kafka.adapter.mysql.coupon

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Table(name="coupon_event")
@EntityListeners(AuditingEntityListener::class)
@DynamicUpdate
@Entity
open class CouponEventEntity (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null,

    @field:Column(name = "display_name")
    private var displayName: String? = null,

    @field:Column(name = "issue_limit")
    private var issueLimit: Long? = null,

    @field:Column(name = "expires_at")
    private var expiresAt: LocalDateTime? = null
){

    open fun getId(): Int? {
        return id
    }

    open fun getDisplayName(): String? {
        return displayName
    }

    open fun getIssueLimit(): Long? {
        return issueLimit
    }

    open fun getExpiresAt(): LocalDateTime? {
        return expiresAt
    }



}