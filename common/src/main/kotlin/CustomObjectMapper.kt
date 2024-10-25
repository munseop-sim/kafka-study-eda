package ms2709.kafka.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class CustomObjectMapper : ObjectMapper(){
    init {
        registerModule(JavaTimeModule())
    }
}