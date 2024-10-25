package ms2709.kafka.adapter.metadata_client.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class MetadataWebClientConfig {
    @Value("\${external-server.metadata.url}")
    private val metadataApiUrl: String? = null

    @Bean
    @Primary
    open fun metadataWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl(metadataApiUrl!!)
            .build()
    }
}