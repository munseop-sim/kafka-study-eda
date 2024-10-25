package ms2709.kafka.usecase.post_resolving_help_usecase

import ms2709.kafka.domain.post.model.ResolvedPost

interface PostResolvingHelpUseCase {
    fun resolvePostById(postId: Long): ResolvedPost?
    fun resolvePostsByIds(postIds: List<Long>): List<ResolvedPost>
}