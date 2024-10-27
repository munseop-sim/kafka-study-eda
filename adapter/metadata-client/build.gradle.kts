plugins {
    kotlin("jvm")
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
