package ms2709.kafka.adapter.kafka.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@EnableKafka
@Configuration
open class KafkaConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.kafka")
    open fun kafkaProperties(): KafkaProperties {
        return KafkaProperties()
    }

    @Bean
    @Primary
    open fun producerFactory(): ProducerFactory<String, Any> {
        val kafkaProperties: KafkaProperties = kafkaProperties()

        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.ACKS_CONFIG] = "-1"
        props[ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG] = "true"
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    @Primary
    open fun kafkaTemplate(): KafkaTemplate<String, *> {
        return KafkaTemplate(producerFactory())
    }
}