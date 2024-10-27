plugins {
    kotlin("jvm")
}


dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.20")
    implementation(project(":usecase:core"))
    testImplementation(kotlin("test"))
}
tasks.test {
    useJUnitPlatform()
}
