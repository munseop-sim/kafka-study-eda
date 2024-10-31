package ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis.post

import ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis.RedisOperator
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate
import ms2709.kafka.usecase.core.port.post.ResolvedPostCachePort
import org.springframework.stereotype.Component
import ms2709.kafka.post.model.ResolvedPost
import java.time.Duration

@Component
class ResolvedPostCacheAdapter(
    private val redisOperator: RedisOperator
): ResolvedPostCachePort {
    private val log by LogDelegate()

    private val KEY_PREFIX: String = "resolved_post:v1:"
    private val EXPIRE_SECONDS: Long = 60 * 60 * 24 * 7L // 일주일
    private val objectMapper = CustomObjectMapper()

    private fun generateCacheKey(postId: Long): String = this.KEY_PREFIX + postId

    override fun set(resolvedPost: ResolvedPost) {
        objectMapper.writeValueAsString(resolvedPost).run {
            redisOperator.set(
                key = generateCacheKey(resolvedPost.id!!),
                value = this,
                timeout = Duration.ofSeconds(EXPIRE_SECONDS)
            )
            log.info("redis set -> {}", resolvedPost)
        }
    }

    override fun get(postId: Long): ResolvedPost? {
        return redisOperator.get(generateCacheKey(postId))?.let {
            val resolvedPost = objectMapper.readValue(it, ResolvedPost::class.java)
            log.info("redis get -> {}", resolvedPost)
            resolvedPost
        }
    }

    override fun multiGet(postIds: List<Long>): List<ResolvedPost> {
        val resolvedPostList = postIds.map {
            generateCacheKey(it)
        }.run {
            val results =  redisOperator.getAll(this)
            results
        }.mapNotNull {
            it ?: return@mapNotNull null
            objectMapper.readValue(it, ResolvedPost::class.java)
        }
        log.info("redis multi get -> {}", resolvedPostList)

        return resolvedPostList
    }

    override fun delete(postId: Long) {
        redisOperator.delete(generateCacheKey(postId))
        log.info("redis delete by key {}", postId)
    }
}