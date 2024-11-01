package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.usecase.core.port.post.OriginalPostMessageProducePort
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ms2709.kafka.post.model.Post


@Service
open class PostCreateService (
    private val postPort: PostPort,
    private val messagePort: OriginalPostMessageProducePort
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
        }.run {
            messagePort.sendCreateMessage(this)
            this
        }
    }

}