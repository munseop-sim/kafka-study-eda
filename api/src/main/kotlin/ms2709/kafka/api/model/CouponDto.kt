package ms2709.kafka.api.model

import java.time.LocalDateTime

data class CouponDto(
    val id: Long,
    val displayName: String,
    val expiresAt: LocalDateTime,
)