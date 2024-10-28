package post.model
import java.time.LocalDateTime

class ResolvedPost{
    var id: Long? = null
    var title: String? = null
    var content: String? = null
    var userId: Long? = null
    var userName: String? = null
    var categoryId: Long? = null
    var categoryName: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var updated:Boolean = false

    companion object{
        fun generate(
            post: Post,
            userName: String?,
            categoryName: String?
        ): ResolvedPost {

            return ResolvedPost().apply {
                this.id=post.id
                this.title=post.title
                this.content=post.content
                this.userId=post.userId
                this.userName=userName
                this.categoryId=post.categoryId
                this.categoryName=categoryName
                this.createdAt = post.createdAt
                this.updatedAt = post.updatedAt
                this.updated = post.createdAt != post.updatedAt
            }

        }
    }

    override fun toString(): String {
        return "ResolvedPost(id=$id, title=$title, content=$content, userId=$userId, userName=$userName, categoryId=$categoryId, categoryName=$categoryName, createdAt=$createdAt, updatedAt=$updatedAt, updated=$updated)"
    }

}