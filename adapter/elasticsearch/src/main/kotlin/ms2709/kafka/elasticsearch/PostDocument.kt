package ms2709.kafka.elasticsearch

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

@Document(indexName = "post-1")
class PostDocument {
    @Id
    var id:Long? = null

    var title:String? = null

    var content:String? = null

    var categoryName:String? = null

    var tags:List<String> = emptyList()

    @Field(type=FieldType.Date, format = [DateFormat.date_hour_minute_second_millis])
    var indexedAt:LocalDateTime? = null


}
