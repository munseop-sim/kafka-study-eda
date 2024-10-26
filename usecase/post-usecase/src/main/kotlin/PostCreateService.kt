package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
open class PostCreateService (
    private val postPort: PostPort
): PostCreateUseCase {

    @Transactional
    override fun create(request: PostCreateUseCase.Request): Post {
        return Post.generate(
            request.userId,
            request.title,
            request.content,
            request.categoryId
        ).run {
            postPort.save(this)
        }
    }

}