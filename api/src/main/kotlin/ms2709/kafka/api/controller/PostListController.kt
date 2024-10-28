package ms2709.kafka.api.controller

import ms2709.kafka.api.model.PostInListDto
import ms2709.kafka.post_search_usecase.PostSearchUseCase

import ms2709.kafka.subscribe_post_usecase.SubscribePostListUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/list")
class PostListController (
    private val subscribePostListUseCase: SubscribePostListUseCase,
    private val postSearchUseCase: PostSearchUseCase
){
    @GetMapping("/inbox/{userId}")
    fun listSubscribingPosts(
        @PathVariable("userId") userId: Long,
        @RequestParam("pageIndex", defaultValue = "0", required = false) pageIndex: Int,
    ): ResponseEntity<List<PostInListDto>> {
        return subscribePostListUseCase.listSubscribingInboxPosts(
            SubscribePostListUseCase.Request(
                followerUserId = userId,
                pageIndex = pageIndex
            )
        ).run {
            this.map {
                PostInListDto(
                  id = it.id!!,
                    title = it.title!!,
                    userName = it.userName!!,
                    createdAt = it.createdAt!!
                )
            }
        }.run {
            ResponseEntity.ok().body(this)
        }
    }

    @GetMapping("/search")
    fun searchPosts(
        @RequestParam("keyword") keyword: String,
        @RequestParam("pageIndex", defaultValue = "0", required = false) pageIndex: Int,
    ): ResponseEntity<List<PostInListDto>> {
        return postSearchUseCase.getSearchResultByKeyword(keyword, pageIndex).map {
            PostInListDto(
                id = it.id!!,
                title = it.title!!,
                userName = it.userName!!,
                createdAt = it.createdAt!!
            )
        }.run {
            return ResponseEntity.ok(this)
        }

    }
}