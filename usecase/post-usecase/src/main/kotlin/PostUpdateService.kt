package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post
import org.springframework.stereotype.Service

@Service
class PostUpdateService : PostUpdateUseCase {
    override fun update(request: PostUpdateUseCase.Request): Post? {
        return null
    }
}