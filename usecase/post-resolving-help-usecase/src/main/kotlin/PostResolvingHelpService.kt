package ms2709.kafka.usecase.post_resolving_help_usecase


import ms2709.kafka.usecase.core.port.metadata.MetadataPort
import ms2709.kafka.usecase.core.port.post.PostPort
import ms2709.kafka.usecase.core.port.post.ResolvedPostCachePort
import org.springframework.stereotype.Service
import post.model.Post
import post.model.ResolvedPost

@Service
open class PostResolvingHelpService (
    private val postPort: PostPort,
    private val metadataPort: MetadataPort,
    private val resolvedPostCachePort: ResolvedPostCachePort
): PostResolvingHelpUseCase {
    override fun resolvePostById(postId: Long): ResolvedPost? {
        val resolvedPost = resolvedPostCachePort.get(postId)
        if(resolvedPost != null) {
            return resolvedPost
        }

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
        ).also {
            resolvedPostCachePort.set(it)
        }
    }

    override fun resolvePostsByIds(postIds: List<Long>): List<ResolvedPost> {
        if(postIds.isEmpty()) {
            return emptyList()
        }
        val resolvedPostList:MutableList<ResolvedPost> = mutableListOf()
        resolvedPostCachePort.multiGet(postIds).run {
            resolvedPostList.addAll(this)
        }

        postIds.filter {
            resolvedPostList.none { resolvedPost -> resolvedPost.id == it }
        }.run {
            postPort.listByIds(this)
        }.map {
            resolvePost(it)
        }.run {
            resolvedPostList.addAll(this)
        }

        return postIds.mapNotNull { id ->
            resolvedPostList.firstOrNull { it.id == id }
        }
    }

    override fun resolvePostAndSave(post: Post) {
        resolvePost(post)
    }

    override fun deleteResolvedPost(postId: Long) {
        resolvedPostCachePort.delete(postId)
    }
}