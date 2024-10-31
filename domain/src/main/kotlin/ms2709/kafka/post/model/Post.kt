package ms2709.kafka.post.model

import java.time.LocalDateTime

class Post {
    constructor()

    private constructor(
        id: Long?,
        title: String?,
        content: String?,
        userId: Long?,
        categoryId: Long?,
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?,
        deletedAt: LocalDateTime? = null
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


    var id:Long? = null
        private set

    var title:String? = null
        private set

    var content: String? = null
        private set

    var userId:Long? = null
        private set

    var categoryId:Long? = null
        private set

    var createdAt: LocalDateTime? = null
        private set

    var updatedAt: LocalDateTime? = null
        private set

    var deletedAt:LocalDateTime? = null
        private set



    fun update(title: String, content: String, categoryId: Long) : Post {
        this.title = title
        this.content = content
        this.categoryId = categoryId
        this.updatedAt = LocalDateTime.now()
        return this
    }

    fun delete(): Post {
        val now = LocalDateTime.now()
        this.updatedAt = now
        this.deletedAt = now
        return this
    }

    fun undelete(): Post {
        this.deletedAt = null
        return this
    }

    companion object {
        fun generate(
            userId: Long?,
            title: String,
            content: String,
            categoryId: Long
        ): Post {
            val now = LocalDateTime.now()
            return Post(id=null,
                title=title,
                content = content,
                userId = userId,
                categoryId = categoryId,
                createdAt = now,
                updatedAt = now
            )
        }


        fun generate(
            id: Long?,
            title: String,
            content: String,
            userId: Long?,
            categoryId: Long?,
            createdAt: LocalDateTime?,
            updatedAt: LocalDateTime?,
            deletedAt: LocalDateTime?
        ): Post {
            return Post(
                id = id,
                title = title,
                content = content,
                userId = userId,
                categoryId = categoryId,
                createdAt = createdAt,
                updatedAt = updatedAt,
                deletedAt = deletedAt
            )
        }

    }


}