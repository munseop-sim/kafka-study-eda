package ms2709.kafka.usecase.core.port.chatgpt

import ms2709.kafka.inspectedpost.model.AutoInspectionResult
import ms2709.kafka.post.model.Post


interface PostAutoInspectPort {
    fun inspect(post: Post, categoryName: String): AutoInspectionResult?
}