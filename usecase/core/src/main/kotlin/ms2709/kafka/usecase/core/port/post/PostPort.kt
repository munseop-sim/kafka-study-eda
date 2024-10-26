package ms2709.kafka.usecase.core.port.post

import ms2709.kafka.domain.post.model.Post

interface PostPort {
    fun save(post: Post): Post
    fun findById(id: Long): Post?
    fun listByIds(ids: List<Long>): List<Post>
}
