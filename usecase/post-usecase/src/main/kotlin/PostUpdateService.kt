package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.usecase.core.port.post.OriginalPostMessageProducePort
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import post.model.Post

@Service
@Transactional
open class PostUpdateService (
    private val postPort: PostPort,
    private val messagePort: OriginalPostMessageProducePort
): PostUpdateUseCase {
    override fun update(request: PostUpdateUseCase.Request): Post? {
        return postPort.findById(request.postId)?.let {
            it.update(
                request.title,
                request.content,
                request.categoryId
            )
            postPort.save(it)
        }?.let {
            messagePort.sendUpdateMessage(it)
            it
        }
    }
}