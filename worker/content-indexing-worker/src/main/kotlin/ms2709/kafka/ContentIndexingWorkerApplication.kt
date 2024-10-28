package ms2709.kafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ContentIndexingWorkerApplication
fun main(args: Array<String>) {
    runApplication<ContentIndexingWorkerApplication>(*args)
}