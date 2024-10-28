plugins {
    kotlin("jvm") version "2.0.20"
}

group = "ms2709.kafka.domain"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(project(":usecase:post-usecase"))
    implementation(project(":usecase:inspected-post-usecase"))
    implementation(project(":usecase:subscribe-post-usecase"))
    implementation(project(":usecase:post-search-usecase"))

    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:chat-gpt-client"))
    implementation(project(":adapter:mongodb"))
    implementation(project(":adapter:elasticsearch"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}