package ms2709.kafka.adapter.mysql.post

import ms2709.kafka.post.model.Post


fun Post.toEntity():PostEntity{
    return PostEntity.generate(
        id= this.id?.toInt(),
        title = this.title,
        content = this.content,
        userId = this.userId?.toInt(),
        categoryId = this.categoryId?.toInt(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt
    )
}

fun PostEntity.toPost(): Post {
    val entity = this
    return Post.generate(
        id = entity.getId()?.toLong(),
        title = entity.getTitle()!!,
        content = entity.getContent()!!,
        userId = entity.getUserId()!!.toLong(),
        categoryId = entity.getCategoryId()?.toLong(),
        createdAt = entity.getCreatedAt(),
        updatedAt = entity.getUpdatedAt(),
        deletedAt = entity.getDeletedAt()
    )
}
