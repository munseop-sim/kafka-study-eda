package ms2709.kafka.usecase.post_resolving_help_usecase

import ms2709.kafka.domain.post.model.ResolvedPost
import ms2709.kafka.usecase.core.MetadataPort
import org.springframework.stereotype.Service

@Service
class PostResolvingHelpService (
    private val metadataPort:MetadataPort
): PostResolvingHelpUseCase {
    override fun resolvePostById(postId: Long): ResolvedPost? {
        val resolvedPost = null
        return resolvedPost
    }

    override fun resolvePostsByIds(postIds: List<Long>): List<ResolvedPost> {
        return listOf()
    }
}