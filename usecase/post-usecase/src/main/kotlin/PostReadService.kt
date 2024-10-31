package ms2709.kafka.usecase.post_usecase

import ms2709.kafka.usecase.post_resolving_help_usecase.PostResolvingHelpUseCase
import org.springframework.stereotype.Service
import ms2709.kafka.post.model.ResolvedPost

@Service
class PostReadService (
    private val postResolvingHelpUseCase: PostResolvingHelpUseCase
): PostReadUseCase {
    override fun getById(id: Long): ResolvedPost? {
        return postResolvingHelpUseCase.resolvePostById(id)
    }
}