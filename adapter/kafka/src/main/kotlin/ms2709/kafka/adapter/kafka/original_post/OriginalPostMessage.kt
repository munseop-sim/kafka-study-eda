package ms2709.kafka.adapter.kafka.original_post

import ms2709.kafka.adapter.kafka.common.OperationTypes
import java.time.LocalDateTime

class OriginalPostMessage {
    var id:Long? = null
    var payLoad:Payload? = null
    var operationType:OperationTypes? = null

    class Payload{
        var id:Long? = null
        var title:String? = null
        var content:String? = null
        var userId:Long? = null
        var categoryId:Long? = null
        var createdAt:LocalDateTime? = null
        var updatedAt:LocalDateTime? = null
        var deletedAt:LocalDateTime? = null
    }
}