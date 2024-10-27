plugins {
//    id("org.springframework.boot") version "3.2.0"
//    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "2.0.20"
//    kotlin("kapt")
//    kotlin("jvm")
}


dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation(project(":usecase:core"))


    testImplementation(kotlin("test"))

//    implementation("com.querydsl:querydsl-jpa:5.0.0")
//    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
//    kapt ("jakarta.annotation:jakarta.annotation-api")
//    kapt ("jakarta.persistence:jakarta.persistence-api")
}
//// Querydsl Q-classes 자동 생성 설정
//sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//    java.srcDir("build/generated/source/kapt/main")
//}
tasks.test {
    useJUnitPlatform()
}
//
//tasks.withType<JavaCompile> {
//    options.annotationProcessorPath = configurations.kapt.get()
//}
//
//
//sourceSets.main {
//    resources.setSrcDirs(listOf("src/main/resources"))
//    java.setSrcDirs(listOf("src/main/kotlin"))
//}
