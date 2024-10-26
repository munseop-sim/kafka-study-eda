package ms2709.kafka.adapter.mysql.post

import ms2709.kafka.domain.post.model.Post

fun Post.toEntity():PostEntity{
    return PostEntity.generate(
        id= this.id,
        title = this.title,
        content = this.content,
        userId = this.userId,
        categoryId = this.categoryId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun PostEntity.toPost(): Post {
    val entity = this
    return Post.generate(
        id = entity.id,
        title = entity.title!!,
        content = entity.content!!,
        userId = entity.userId!!,
        categoryId = entity.categoryId,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt,
        deletedAt = entity.deletedAt
    )
}
