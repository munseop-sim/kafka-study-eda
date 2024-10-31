package ms2709.kafka.usecase.core.port.post

import ms2709.kafka.inspectedpost.model.InspectedPost

interface PostSearchPort {
    fun indexPost(post: InspectedPost)
    fun deletePost(id: Long)
    fun searchPostIdsByKeyword(keyword: String, pageIndex: Int = 0, pageSize: Int): List<Long>
}