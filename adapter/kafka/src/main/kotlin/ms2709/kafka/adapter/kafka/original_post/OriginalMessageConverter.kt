package ms2709.kafka.adapter.kafka.original_post

import ms2709.kafka.adapter.kafka.common.OperationTypes
import post.model.Post


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
        this.payLoad = OriginalPostMessage.Payload().apply {
            this.id= post.id
            this.title = post.title
            this.content = post.content
            this.userId = post.userId
            this.categoryId = post.categoryId
            this.createdAt = post.createdAt
            this.updatedAt = post.updatedAt
            this.deletedAt = post.deletedAt
        }


        this.operationType = operationType
    }
}