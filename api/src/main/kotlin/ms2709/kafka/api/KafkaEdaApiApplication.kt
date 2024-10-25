package ms2709.kafka.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["ms2709.kafka"])
@SpringBootApplication
open class KafkaEdaApiApplication
fun main(args: Array<String>) {
    runApplication<KafkaEdaApiApplication>(*args)
}