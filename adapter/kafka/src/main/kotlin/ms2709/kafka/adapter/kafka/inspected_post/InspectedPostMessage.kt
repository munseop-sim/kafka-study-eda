package ms2709.kafka.adapter.kafka.inspected_post

import ms2709.kafka.adapter.kafka.common.OperationTypes
import ms2709.kafka.post.model.Post

import java.time.LocalDateTime

class InspectedPostMessage {
    var id:Long? = null
    var payLoad:Payload? = null
    var operationType:OperationTypes? = null

    class Payload{
        var post: Post? = null
        var categoryName:String? = null
        var autoGeneratedTags:MutableList<String> = mutableListOf()
        var inspectedAt:LocalDateTime = LocalDateTime.now()
    }

    override fun toString(): String {
        return "InspectedPostMessage(id=$id, payLoad=$payLoad, operationType=$operationType)"
    }


}