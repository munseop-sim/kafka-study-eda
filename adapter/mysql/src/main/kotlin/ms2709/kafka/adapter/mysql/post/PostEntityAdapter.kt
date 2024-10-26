package ms2709.kafka.adapter.mysql.post

import ms2709.kafka.domain.post.model.Post
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

//todo - querydsl 추가
@Component
class PostEntityAdapter(
    private val postJpaRepository: PostJpaRepository
) : PostPort {
    override fun save(post: Post): Post {
        return post.toEntity().run {
            postJpaRepository.save(this)
        }.run {
            this.toPost()
        }
    }

    override fun findById(id: Long): Post? {
        return postJpaRepository.findByIdOrNull(id)?.let { it.toPost() }
    }

    override fun listByIds(ids: List<Long>): List<Post> {
        return postJpaRepository.findAllById(ids).map { it.toPost() }
    }
}