package ms2709.kafka.adapter.mysql.post

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Table(name="post")
@EntityListeners(AuditingEntityListener::class)
@DynamicUpdate
@Entity
open class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var title: String? = null
    var content: String? = null

    @Column(name="user_id")
    var userId: Long? = null

    @Column(name="category_id")
    var categoryId: Long? = null

    @Column(name="created_at")
    @CreatedDate
    var createdAt: LocalDateTime? = null

    @Column(name="updated_at")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @Column(name="deleted_at")
    var deletedAt: LocalDateTime? = null


    companion object {
        fun generate(
            id:Long?,
            title:String?,
            content:String?,
            userId: Long?,
            categoryId:Long?,
            createdAt: LocalDateTime?,
            updatedAt: LocalDateTime?,
            deletedAt: LocalDateTime?
        ):PostEntity {
            return PostEntity().apply {
                this.id = id
                this.title = title
                this.content = content
                this.userId = userId
                this.categoryId = categoryId
                this.createdAt = createdAt
                this.updatedAt = updatedAt
                this.deletedAt = deletedAt
            }
        }
    }

}