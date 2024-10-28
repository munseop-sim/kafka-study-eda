dependencies{
    implementation(project(":usecase:post-search-usecase"))

    implementation(project(":adapter:kafka"))
    implementation(project(":adapter:mysql"))
    implementation(project(":adapter:elasticsearch"))
    implementation(project(":adapter:metadata-client"))
}