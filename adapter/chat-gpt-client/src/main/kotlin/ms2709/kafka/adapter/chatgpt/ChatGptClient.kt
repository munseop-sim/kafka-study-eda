package ms2709.kafka.adapter.chatgpt

import ms2709.kafka.common.CustomObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.List

@Component
class ChatGptClient(
    @Qualifier("chatGptWebClient") private val chatGptWebClient: WebClient,
){
    private val targetGptModel="gpt-4o-mini"

    @Value("\${OPENAI_API_KEY}")
    private lateinit var openaiApiKey: String

    private val objectMapper: CustomObjectMapper = CustomObjectMapper()

    fun testChatGpt(content: String): String {
        return chatGptWebClient
            .post()
            .uri("/v1/chat/completions")
            .header("Authorization", "Bearer $openaiApiKey") // OpenAI API 키 추가
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                java.util.Map.of<String, Any>(
                    "model", targetGptModel,
                    "messages", List.of<Map<String, String>>(
                        java.util.Map.of<String, String>("role", "system", "content", "You are an assistant."),
                        java.util.Map.of<String, String>("role", "user", "content", content)
                    ),
                    "stream", false
                )
            )
            .retrieve()
            .bodyToMono<String>(String::class.java)
            .block()
    }
}