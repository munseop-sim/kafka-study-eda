package ms2709.kafka.adapter.chatgpt.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class ChatGptWebClientConfig {
    @Bean
    @Qualifier("chatGptWebClient")
    open fun chatGptWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.openai.com")
            .build()
    }
}