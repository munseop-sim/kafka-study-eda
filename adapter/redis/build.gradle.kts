plugins {
    kotlin("jvm")
}


dependencies {
    api("org.springframework.boot:spring-boot-starter-data-redis:3.2.0")
    // https://mvnrepository.com/artifact/org.redisson/redisson
    api("org.redisson:redisson:3.37.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
