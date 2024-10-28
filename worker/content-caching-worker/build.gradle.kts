plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":usecase:post-resolving-help-usecase"))

    implementation(project(":adapter:redis"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:redis"))
    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:metadata-client"))
}





//
//implementation(project(":common"))
//implementation(project(":domain"))
//
//implementation(project(":usecase:core"))
//implementation(project(":usecase:subscribing-post-usecase"))
//
//implementation(project(":adapter:kafka"))
//implementation(project(":adapter:mysql"))
//implementation(project(":adapter:mongodb"))
//implementation(project(":adapter:redis"))
//implementation(project(":adapter:metadata-client"))