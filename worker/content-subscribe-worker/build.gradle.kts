plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":usecase:subscribe-post-usecase"))

    implementation(project(":adapter:metadata-client"))
    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:chat-gpt-client"))
    implementation(project(":adapter:mongodb"))
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