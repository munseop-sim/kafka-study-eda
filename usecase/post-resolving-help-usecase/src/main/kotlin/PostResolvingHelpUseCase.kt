package ms2709.kafka.usecase.post_resolving_help_usecase

import ms2709.kafka.post.model.Post
import ms2709.kafka.post.model.ResolvedPost

interface PostResolvingHelpUseCase {
    fun resolvePostById(postId: Long): ResolvedPost?
    fun resolvePostsByIds(postIds: List<Long>): List<ResolvedPost>
    fun resolvePostAndSave(post: Post)
    fun deleteResolvedPost(postId: Long)
}