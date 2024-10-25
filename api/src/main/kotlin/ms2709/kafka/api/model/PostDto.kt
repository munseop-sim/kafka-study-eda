package ms2709.kafka.api.model

import java.time.LocalDateTime

class PostDto {

    constructor(
        id: Long?,
        title: String?,
        content: String?,
        userId: Long?,
        categoryId: Long?,
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?,
        deletedAt: LocalDateTime?
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.userId = userId
        this.categoryId = categoryId
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.deletedAt = deletedAt
    }

    var id: Long? = null
    var title: String? = null
    var content: String? = null
    var userId: Long? = null
    var categoryId: Long? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
    var deletedAt: LocalDateTime? = null
}