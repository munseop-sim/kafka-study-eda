plugins {
    kotlin("jvm")
}


dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
