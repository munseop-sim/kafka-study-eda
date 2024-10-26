package ms2709.kafka.api.controller

import ms2709.kafka.adapter.chatgpt.ChatGptClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal")
class InternalController (
    private val chatGptClient: ChatGptClient
){

    @GetMapping("/test/chat-gpt")
    fun test(
        @RequestParam("content") content: String
    ): String {
        return chatGptClient.testChatGpt(content)
    }
}