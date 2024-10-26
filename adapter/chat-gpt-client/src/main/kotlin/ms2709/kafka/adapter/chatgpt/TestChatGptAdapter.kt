package ms2709.kafka.adapter.chatgpt

import ms2709.kafka.usecase.core.port.chatgpt.TestChatGptPort
import org.springframework.stereotype.Component

@Component
class TestChatGptAdapter(
    private val chatGptClient: ChatGptClient
) : TestChatGptPort {
    override fun test(content: String): String? {
        return chatGptClient.testChatGpt(content)
    }
}