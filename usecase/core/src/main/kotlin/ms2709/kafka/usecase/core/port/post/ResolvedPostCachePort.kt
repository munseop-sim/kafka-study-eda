package ms2709.kafka.usecase.core.port.post

import post.model.ResolvedPost

interface ResolvedPostCachePort {
    fun set(resolvedPost: ResolvedPost)
    fun get(postId: Long): ResolvedPost?
    fun multiGet(postIds: List<Long>): List<ResolvedPost>
    fun delete(postId: Long)
}