package ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis.coupon

import ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis.RedisOperator
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.coupon.model.CouponEvent
import ms2709.kafka.usecase.core.port.coupon.CouponEventCachePort
import ms2709.kafka.usecase.core.port.coupon.CouponIssueRequestHistoryPort
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CouponCacheAdapter(
    private val redisOperator:RedisOperator
): CouponEventCachePort, CouponIssueRequestHistoryPort {

    private val USER_REQUEST_HISTORY_KEY_PREFIX: String = "coupon_history.user_request.v1"
    private val REQUEST_COUNT_HISTORY_KEY_PREFIX: String = "coupon_history.request_count.v1"

    private val EXPIRE_SECONDS: Long = 60 * 60 * 24 * 7L // 일주일

    private val objectMapper = CustomObjectMapper()


    override fun set(couponEvent: CouponEvent) {
        redisOperator.set(
            getCouponEventCacheKey(couponEvent.id!!),
            objectMapper.writeValueAsString(couponEvent),
            timeout = Duration.ofSeconds(60L*10L) // timeout 10분
        )
    }

    override fun get(couponEventId: Long): CouponEvent? {
        return redisOperator.get(getCouponEventCacheKey(couponEventId!!))?.let {
            objectMapper.readValue(it, CouponEvent::class.java)
        }
    }

    override fun setHistoryIfNotExists(couponEventId: Long, userId: Long): Boolean {
        return redisOperator.setIfAbsent(
            "$USER_REQUEST_HISTORY_KEY_PREFIX:${couponEventId}:${userId}",
            "1",
            Duration.ofSeconds(EXPIRE_SECONDS)
        )
    }

    override fun getRequestSequentialNumber(couponEventId: Long): Long {
        val key = "$REQUEST_COUNT_HISTORY_KEY_PREFIX:${couponEventId}"
        return redisOperator.increment(key).also {
            if(it == 1L){
                redisOperator.setExpiration(key, Duration.ofSeconds(EXPIRE_SECONDS))
            }
        }
    }


    private fun getCouponEventCacheKey(couponEventId:Long):String = "coupon_event:v1:${couponEventId}"
}