package ms2709.kafka.adapter.mysql.coupon

import ms2709.kafka.coupon.model.Coupon
import ms2709.kafka.coupon.model.CouponEvent
import ms2709.kafka.coupon.model.ResolvedCoupon

fun Coupon.toEntity() : CouponEntity {
    val dto = this
    return CouponEntity(
        id = dto.id?.toInt(),
        userId = dto.userId?.toInt(),
        usedAt = dto.usedAt,
        issuedAt = dto.issuedAt,
        couponEventId = dto.couponEventId?.toInt(),
    )
}

fun CouponEntity.toModel() : Coupon {
    val entity = this
    return Coupon().apply {
        this.id = entity.getId()?.toLong()
        this.userId = entity.getUserId()?.toLong()
        this.usedAt = entity.getUsedAt()
        this.issuedAt = entity.getIssuedAt()
        this.couponEventId = entity.getCouponEventId()?.toLong()
    }
}

fun CouponEntity.toResolvedCoupon() : ResolvedCoupon {
    val entity = this
    return ResolvedCoupon().apply {
        this.coupon = entity.toModel()
        this.couponEvent = entity.getCouponEvent()!!.toModel()
    }
}

fun CouponEventEntity.toModel() : CouponEvent {
    val entity = this
    return CouponEvent().apply {
        this.id = entity.getId()?.toLong()
        this.displayName = entity.getDisplayName()
        this.expiresAt = entity.getExpiresAt()
        this.issueLimit = entity.getIssueLimit()
    }
}