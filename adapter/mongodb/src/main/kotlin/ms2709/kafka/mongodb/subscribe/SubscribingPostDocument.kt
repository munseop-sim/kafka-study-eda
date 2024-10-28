package ms2709.kafka.mongodb.subscribe


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import post.model.Post
import java.time.LocalDateTime

@Document(collection = "subscribingInboxPosts")
class SubscribingPostDocument {
    @Id
    lateinit var id: String

    var postId:Long? = null

    var followerUserId:Long? = null

    lateinit var postCreatedAt: LocalDateTime

    lateinit var addedAt:LocalDateTime

    var read:Boolean? = null

    companion object{
        fun generate(
            post: Post,
            followerUserId:Long,
        ) : SubscribingPostDocument{
            return SubscribingPostDocument().apply {
                this.id = "${post.id}_${followerUserId}"
                this.postId = post.id!!
                this.followerUserId = followerUserId
                this.postCreatedAt = post.createdAt!!
                this.addedAt = LocalDateTime.now()
                this.read = false
            }
        }
    }
}
