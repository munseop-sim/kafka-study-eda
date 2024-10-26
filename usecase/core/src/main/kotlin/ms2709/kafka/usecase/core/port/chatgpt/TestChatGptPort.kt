package ms2709.kafka.usecase.core.port.chatgpt

interface TestChatGptPort {
    fun test(content: String): String?
}