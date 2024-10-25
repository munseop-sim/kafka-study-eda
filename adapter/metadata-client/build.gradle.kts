plugins {
    kotlin("jvm")
}


dependencies {
//    api("org.springframework.boot:spring-boot-starter-data-redis:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
