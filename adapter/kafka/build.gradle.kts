plugins {
    kotlin("jvm")
}


dependencies {
    implementation("org.springframework.kafka:spring-kafka:3.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
