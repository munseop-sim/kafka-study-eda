package ms2709.kafka.worker.auto_inspection_worker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["ms2709.kafka"])
@SpringBootApplication
open class AutoInspectionWorkerApplication
fun main(args: Array<String>) {
    runApplication<AutoInspectionWorkerApplication>(*args)
}
