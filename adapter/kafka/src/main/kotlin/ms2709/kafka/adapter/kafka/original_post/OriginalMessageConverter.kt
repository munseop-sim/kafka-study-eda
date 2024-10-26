package ms2709.kafka.adapter.kafka.original_post

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.domain.post.model.Post

fun OriginalPostMessage.toModel(): Post {
    return Post.generate(
        this.id!!,
        this.payLoad?.title ?: throw Exception("title is must not null"),
        this.payLoad?.content ?: throw Exception("content is must not null"),
        this.payLoad?.userId,
        this.payLoad?.categoryId,
        this.payLoad?.createdAt,
        this.payLoad?.updatedAt,
        this.payLoad?.deletedAt
    )
}

fun Post.toMessage(id:Long, operationType:OperationTypes): OriginalPostMessage {
    val post = this
    return OriginalPostMessage().apply {
        this.id = id
        this.payLoad = OriginalPostMessage.Payload(
            id= post.id,
            title = post.title,
            content = post.content,
            userId = post.userId,
            categoryId = post.categoryId,
            createdAt = post.createdAt,
            updatedAt = post.updatedAt,
            deletedAt = post.deletedAt
        )
        this.operationType = operationType
    }
}