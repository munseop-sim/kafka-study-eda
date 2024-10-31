plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation(project(":usecase:core"))
}