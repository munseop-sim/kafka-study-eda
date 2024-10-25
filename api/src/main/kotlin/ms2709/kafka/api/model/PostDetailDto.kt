package ms2709.kafka.api.model

import java.time.LocalDateTime

class PostDetailDto {
    constructor(
        id: Long?,
        title: String?,
        content: String?,
        userName: String?,
        categoryName: String?,
        createdAt: LocalDateTime?,
        updated: Boolean
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.userName = userName
        this.categoryName = categoryName
        this.createdAt = createdAt
        this.updated = updated
    }

    var id: Long? = null
    var title: String? = null
    var content: String? = null
    var userName: String? = null
    var categoryName: String? = null
    var createdAt: LocalDateTime? = null
    var updated = false
}