//package ms2709.kafka.adapter.mysql.post
//
//import jakarta.persistence.PostPersist
//import jakarta.persistence.PostRemove
//import jakarta.persistence.PostUpdate
//import ms2709.kafka.common.LogDelegate
//import ms2709.kafka.usecase.core.port.post.OriginalPostMessageProducePort
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//import org.springframework.context.annotation.Lazy;
//@Component
//class PostEntityListener(
//    @Lazy @Autowired private val messagePort: OriginalPostMessageProducePort
//) {
//    private val log by LogDelegate()
//
//    @PostPersist
//    fun onPostPersist(entity:PostEntity){
//        log.info("send kafka produce -> createMessage")
//        messagePort.sendCreateMessage(entity.toPost())
//    }
//
//    @PostUpdate
//    fun onPostUpdate(entity:PostEntity){
//        if(entity.deletedAt == null){
//            log.info("send kafka produce -> updatedMessage")
//            messagePort.sendUpdateMessage(entity.toPost())
//        }else{
//            log.info("send kafka produce -> deletedMessage")
//            messagePort.sendDeleteMessage(entity.id!!)
//        }
//    }
//}