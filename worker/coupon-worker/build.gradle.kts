plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":usecase:coupon-usecase"))

    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:redis"))
}



