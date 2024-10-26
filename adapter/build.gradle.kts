plugins {
    kotlin("kapt") version "2.0.20"
    kotlin("jvm") version "2.0.20"
}

allprojects {
    repositories {
        mavenCentral() // 모든 프로젝트에서 사용할 저장소 정의
    }
}

subprojects{
    apply(plugin = "org.jetbrains.kotlin.jvm") // 서브 프로젝트에 공통으로 코틀린 플러그인 적용
    apply(plugin = "org.jetbrains.kotlin.kapt")

    dependencies{
//        testImplementation(kotlin("test"))
        implementation(kotlin("stdlib")) // 코틀린 표준 라이브러리 사용
        implementation("org.springframework.boot:spring-boot-starter:3.2.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
        implementation(project(":common"))
        implementation(project(":domain"))
        implementation(project(":usecase:core"))
    }
    kotlin {
        jvmToolchain(21)
    }

    kapt {
        correctErrorTypes = true
    }
}
