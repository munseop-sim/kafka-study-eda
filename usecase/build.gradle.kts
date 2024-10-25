plugins {
    kotlin("jvm") version "2.0.20"
}

allprojects {
    repositories {
        mavenCentral() // 모든 프로젝트에서 사용할 저장소 정의
    }
}

subprojects{
    apply(plugin = "org.jetbrains.kotlin.jvm") // 서브 프로젝트에 공통으로 코틀린 플러그인 적용

    dependencies{
        api(kotlin("stdlib")) // 코틀린 표준 라이브러리 사용
        api("org.springframework.boot:spring-boot-starter:3.2.0")
        implementation(project(":common"))
        implementation(project(":domain"))
    }
    kotlin {
        jvmToolchain(21)
    }
}
