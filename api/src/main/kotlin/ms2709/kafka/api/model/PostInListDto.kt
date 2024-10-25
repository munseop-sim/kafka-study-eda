package ms2709.kafka.api.model

import java.time.LocalDateTime

class PostInListDto (
    val id: Long,
    val title: String,
    val userName: String,
    val createdAt: LocalDateTime
){
    // 목록에 노출하기 위한 용도의 요약버전




}