package ms2709.kafka.usecase.core.port.chatgpt

import inspectedpost.model.AutoInspectionResult
import ms2709.kafka.domain.post.model.Post

interface PostAutoInspectPort {
    fun inspect(post: Post, categoryName: String): AutoInspectionResult?
}