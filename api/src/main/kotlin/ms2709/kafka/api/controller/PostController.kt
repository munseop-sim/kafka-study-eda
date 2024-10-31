package ms2709.kafka.api.controller

import ms2709.kafka.api.model.PostCreateRequest
import ms2709.kafka.api.model.PostDetailDto
import ms2709.kafka.api.model.PostDto
import ms2709.kafka.api.model.PostUpdateRequest

import ms2709.kafka.usecase.post_usecase.PostCreateUseCase
import ms2709.kafka.usecase.post_usecase.PostDeleteUseCase
import ms2709.kafka.usecase.post_usecase.PostReadUseCase
import ms2709.kafka.usecase.post_usecase.PostUpdateUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ms2709.kafka.post.model.Post
import ms2709.kafka.post.model.ResolvedPost


@RestController
@RequestMapping("/posts")
class PostController(
    private val postCreateUsecase: PostCreateUseCase,
    private val postUpdateUsecase: PostUpdateUseCase,
    private val postDeleteUsecase: PostDeleteUseCase,
    private val postReadUsecase: PostReadUseCase
) {
    @PostMapping
    fun createPost(
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<PostDto> {
        val post: Post = postCreateUsecase.create(
            PostCreateUseCase.Request(
                request.userId!!,
                request.title!!,
                request.content!!,
                request.categoryId!!
            )
        )
        return ResponseEntity.ok().body<PostDto>(toDto(post))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable("postId") id: Long,
        @RequestBody request: PostUpdateRequest
    ): ResponseEntity<PostDto> {
        return postUpdateUsecase.update(
            PostUpdateUseCase.Request(
                id,
                request.title!!,
                request.content!!,
                request.categoryId!!
            )
        )?.let {
            ResponseEntity.ok().body(toDto(it))
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable("postId") id: Long
    ): ResponseEntity<PostDto> {
        return postDeleteUsecase.delete(
            PostDeleteUseCase.Request(id)
        )?.let {
            ResponseEntity.ok().body(toDto(it))
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/{postId}/detail")
    fun readPostDetail(
        @PathVariable("postId") id: Long?
    ): ResponseEntity<PostDetailDto> {
        val resolvedPost = postReadUsecase.getById(id!!) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(toDto(resolvedPost))
    }

    private fun toDto(post: Post): PostDto {
        return PostDto(
            post.id,
            post.title,
            post.content,
            post.userId,
            post.categoryId,
            post.createdAt,
            post.updatedAt,
            post.deletedAt
        )
    }


    private fun toDto(resolvedPost: ResolvedPost): PostDetailDto {
        return PostDetailDto(
            resolvedPost.id,
            resolvedPost.title,
            resolvedPost.content,
            resolvedPost.userName,
            resolvedPost.categoryName,
            resolvedPost.createdAt,
            resolvedPost.updated
        )
    }

}