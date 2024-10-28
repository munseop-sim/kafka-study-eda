package inspectedpost.model


import post.model.Post
import java.time.LocalDateTime

data class InspectedPost (
    val post: Post,
    val categoryName:String,
    val autoGeneratedTags:List<String>,
    val inspectedAt: LocalDateTime = LocalDateTime.now()
)