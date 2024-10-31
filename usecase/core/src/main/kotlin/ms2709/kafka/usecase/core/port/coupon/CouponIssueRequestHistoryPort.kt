package ms2709.kafka.usecase.core.port.coupon

interface CouponIssueRequestHistoryPort {
    // 해당 쿠폰이벤트 내에서, 유저의 발급 요청이력이 없다면 기록
    fun setHistoryIfNotExists(couponEventId: Long, userId: Long): Boolean

    // 해당 쿠폰이벤트 내에서, 발급 요청을 몇번째로 했는지 확인
    fun getRequestSequentialNumber(couponEventId: Long): Long
}