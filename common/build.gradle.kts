plugins {
    kotlin("jvm") version "2.0.20"
}

group = "ms2709.kafka.common"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.3")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    api("ch.qos.logback:logback-classic:1.4.11")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-to-slf4j
    api("org.apache.logging.log4j:log4j-to-slf4j:2.21.1")
    // https://mvnrepository.com/artifact/org.slf4j/jul-to-slf4j
    api("org.slf4j:jul-to-slf4j:2.0.9")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}