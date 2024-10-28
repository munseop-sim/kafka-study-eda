package ms2709.kafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ContentCachingWorkerApplication
fun main(args: Array<String>) {
    runApplication<ContentCachingWorkerApplication>(*args)
}