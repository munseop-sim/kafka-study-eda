plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":usecase:core"))
    implementation(project(":usecase:post-resolving-help-usecase"))
}