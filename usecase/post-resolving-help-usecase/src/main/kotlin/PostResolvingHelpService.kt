package ms2709.kafka.usecase.post_resolving_help_usecase

import ms2709.kafka.domain.post.model.Post
import ms2709.kafka.domain.post.model.ResolvedPost
import ms2709.kafka.usecase.core.port.metadata.MetadataPort
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.stereotype.Service

@Service
class PostResolvingHelpService (
    private val postPort: PostPort,
    private val metadataPort: MetadataPort
): PostResolvingHelpUseCase {
    override fun resolvePostById(postId: Long): ResolvedPost? {
        return postPort.findById(postId)?.let {
            this.resolvePost(it)
        }
    }

    private fun resolvePost(post: Post):ResolvedPost {
        return ResolvedPost.generate(
            post,
            post.userId?.let{
                metadataPort.getUserNameByUserId(it)
            },
            post.categoryId?.let {
                metadataPort.getCategoryNameByCategoryId(it)
            }
        )
    }

    override fun resolvePostsByIds(postIds: List<Long>): List<ResolvedPost> {
        return listOf()
    }
}