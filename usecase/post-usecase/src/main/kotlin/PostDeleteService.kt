package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post
import ms2709.kafka.usecase.core.port.post.OriginalPostMessageProducePort
import ms2709.kafka.usecase.core.port.post.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
open class PostDeleteService (
    private val postPort: PostPort,
    private val messagePort: OriginalPostMessageProducePort
): PostDeleteUseCase {
    override fun delete(request: PostDeleteUseCase.Request): Post? {
        return postPort.findById(request.postId)?.let {
            it.delete()
            postPort.save(it)
        }?.let {
            messagePort.sendDeleteMessage(it.id!!)
           it
        }
    }
}