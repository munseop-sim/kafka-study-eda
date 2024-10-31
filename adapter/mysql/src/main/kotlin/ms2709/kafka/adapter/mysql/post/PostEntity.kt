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
open class PostEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null,

    @Column(name="title")
    private var title: String? = null,

    @Column(name="content")
    private var content: String? = null,

    @Column(name="user_id")
    private var userId: Int? = null,

    @Column(name="category_id")
    private var categoryId: Int? = null,

    @Column(name="created_at")
    @CreatedDate
    private var createdAt: LocalDateTime? = null,

    @Column(name="updated_at")
    @LastModifiedDate
    private var updatedAt: LocalDateTime? = null,

    @Column(name="deleted_at")
    private var deletedAt: LocalDateTime? = null
){


    open fun getId(): Int? {
        return this.id
    }

    open fun getTitle(): String? {
        return this.title
    }

    open fun getContent(): String? {
        return this.content
    }

    open fun getUserId(): Int? {
        return this.userId
    }

    open fun getCategoryId(): Int? {
        return this.categoryId
    }

    open fun getCreatedAt(): LocalDateTime? {
        return this.createdAt
    }

    open fun getUpdatedAt(): LocalDateTime? {
        return this.updatedAt
    }

    open fun getDeletedAt(): LocalDateTime? {
        return this.deletedAt
    }
    
    
    companion object {
        fun generate(
            id:Int?,
            title:String?,
            content:String?,
            userId: Int?,
            categoryId:Int?,
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