package ms2709.kafka.api.controller

import ms2709.kafka.api.model.PostInListDto
import ms2709.kafka.domain.post.model.ResolvedPost
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/list")
class PostListController {
    @GetMapping("/inbox/{userId}")
    fun listSubscribingPosts(
        @PathVariable("userId") userId: Long?
    ): ResponseEntity<List<PostInListDto>> {
        return ResponseEntity.internalServerError().build<List<PostInListDto>>()
    }

    @GetMapping("/search")
    fun searchPosts(
        @RequestParam("query") query: String?
    ): ResponseEntity<List<PostInListDto>> {
        return ResponseEntity.internalServerError().build()
    }
    private fun toDto(resolvedPost: ResolvedPost): PostInListDto {
        return PostInListDto(
            resolvedPost.id!!,
            resolvedPost.title!!,
            resolvedPost.userName!!,
            resolvedPost.createdAt!!
        )
    }
}