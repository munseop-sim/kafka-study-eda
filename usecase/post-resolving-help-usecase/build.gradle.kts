plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":usecase:core"))
    implementation(project(":adapter:redis"))
}