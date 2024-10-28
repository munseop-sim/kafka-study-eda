package ms2709.kafka.adapter.redis.ms2709.kafka.adapter.redis.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
open class RedisConfiguration {
    @Value("\${spring.data.redis.host}")
    private val host: String? = null
    @Value("\${spring.data.redis.port}")
    private val port: Int? = null


    /*Lettuce*/
    @Bean
    open fun redisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host!!
        redisStandaloneConfiguration.port = port!!
//        redisStandaloneConfiguration.setPassword(password!!)

        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    /**
     * redis key 는 String 타입으로 국한한다.
     */
    @Bean(name = ["redisTemplate"])
    open fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.setDefaultSerializer(StringRedisSerializer())
        redisTemplate.isEnableDefaultSerializer = true

        return redisTemplate
    }

    @Bean
    open fun redissonClient(): RedissonClient {
        val config = Config()
        config.useSingleServer().address = "redis://$host:$port"
//        config.useSingleServer().password = this.password!!
        return Redisson.create(config)
    }

}