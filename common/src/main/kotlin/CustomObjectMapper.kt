package ms2709.kafka.common

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class CustomObjectMapper : ObjectMapper(){
    init {
        registerModule(JavaTimeModule())
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            this.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)

    }
}