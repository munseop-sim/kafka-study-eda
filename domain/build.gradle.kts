plugins {
    kotlin("jvm") version "2.0.20"
}

group = "ms2709.kafka.domain"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}