package ms2709.kafka.adapter.mysql.coupon

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CouponEntityRepository : JpaRepository<CouponEntity, Long> {
    @EntityGraph("couponEvent")
    fun findAllByUserId(userId: Long): List<CouponEntity>
}