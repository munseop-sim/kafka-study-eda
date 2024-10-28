package ms2709.kafka.usecase.core.port.post

import post.model.Post

interface PostPort {
    fun save(post: Post): Post
    fun findById(id: Long): Post?
    fun listByIds(ids: List<Long>): List<Post>
}
