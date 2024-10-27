plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.springframework.kafka:spring-kafka:3.2.0")
    implementation(project(":usecase:inspected-post-usecase"))

    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:chat-gpt-client"))
}