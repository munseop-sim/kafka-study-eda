package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post
import org.springframework.stereotype.Service

@Service
class PostDeleteService : PostDeleteUseCase {
    override fun delete(request: PostDeleteUseCase.Request): Post? {
        return null
    }
}