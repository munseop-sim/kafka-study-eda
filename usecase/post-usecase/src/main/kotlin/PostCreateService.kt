package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.domain.post.model.Post
import org.springframework.stereotype.Service

@Service
class PostCreateService : PostCreateUseCase {
    override fun create(request: PostCreateUseCase.Request): Post {
        return Post.generate(
            request.userId,
            request.title,
            request.content,
            request.categoryId
        )
    }

}