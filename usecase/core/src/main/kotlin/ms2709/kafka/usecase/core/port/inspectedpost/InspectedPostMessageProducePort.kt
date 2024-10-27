package ms2709.kafka.usecase.core.port.inspectedpost

import inspectedpost.model.InspectedPost

interface InspectedPostMessageProducePort {
    fun sendCreateMessage(inspectedPost: InspectedPost)
    fun sendUpdateMessage(inspectedPost: InspectedPost)
    fun sendDeleteMessage(id: Long)
}