package ms2709.kafka.domain.post.model

import java.time.LocalDateTime

class ResolvedPost(
    val id: Long?,
    val title: String?,
    val content: String?,
    val userId: Long?,
    val userName: String?,
    val categoryId: Long?,
    val categoryName: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val updated:Boolean = false
) {

    companion object{
        fun generate(
            post: Post,
            userName: String?,
            categoryName: String?
        ): ResolvedPost {

            return ResolvedPost(
                id=post.id,
                title=post.title,
                content=post.content,
                userId=post.userId,
                userName=userName,
                categoryId=post.categoryId,
                categoryName=categoryName,
                createdAt = post.createdAt,
                updatedAt = post.updatedAt,
                updated = post.createdAt != post.updatedAt
            )
        }
    }



}