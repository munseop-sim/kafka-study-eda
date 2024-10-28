plugins {
    kotlin("jvm")
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch:3.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
