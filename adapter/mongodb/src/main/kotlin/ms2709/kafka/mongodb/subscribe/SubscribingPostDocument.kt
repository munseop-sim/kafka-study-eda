package ms2709.kafka.mongodb.subscribe

import ms2709.kafka.domain.post.model.Post
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import kotlin.properties.Delegates

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



//@Id
//private String id; // postId와 followerUserId의 조합
//
//private Long postId;
//private Long followerUserId; // follower(구독자) user id
//private LocalDateTime postCreatedAt; // 컨텐츠의 생성시점
//private LocalDateTime addedAt; // follower 유저의 구독 목록에 반영된 시점
//private boolean read; // 해당 구독 컨텐츠 조회 여부