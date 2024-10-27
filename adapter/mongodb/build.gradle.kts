plugins {
    kotlin("jvm")
}


dependencies {
//    api("org.springframework.boot:spring-boot-starter-data-redis:3.2.0")
    api("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.0")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
