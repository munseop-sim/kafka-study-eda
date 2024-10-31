package ms2709.kafka.adapter.mysql.coupon

import org.springframework.data.jpa.repository.JpaRepository

interface CouponEventEntityRepository : JpaRepository<CouponEventEntity, Long> {
}