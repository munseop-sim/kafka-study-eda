package ms2709.kafka.api.controller

import inspectedpost.model.InspectedPost
import io.swagger.v3.oas.annotations.Operation
import ms2709.kafka.adapter.chatgpt.ChatGptClient

import ms2709.kafka.usecase.inspected_post_usecase.PostInspectUseCase
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import post.model.Post

@RestController
@RequestMapping("/internal")
class InternalController (
    private val chatGptClient: ChatGptClient,
    private val postInspectUseCase: PostInspectUseCase
){

    @Operation(description = "chat-gpt-test")
    @GetMapping("/test/chat-gpt")
    fun test(
        @RequestParam("content") content: String
    ): String {
        return chatGptClient.testChatGpt(content)
    }

    @Operation(description = "chat-gpt를 이용하여 post의 주제에 맞는 내용인지 확인 후에 태그 추출")
    @GetMapping("/inspect-post")
    fun postInspectionTest(
        @RequestParam("title") title: String,
        @RequestParam("content") content: String,
        @RequestParam("categoryId") categoryId: Long,
    ): ResponseEntity<InspectedPost>{
        return postInspectUseCase.inspectAndGetIfValid(
            Post.generate(
                userId = null,
                title = title,
                content = content,
                categoryId = categoryId
            )
        )?.let {
            return ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}