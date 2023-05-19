dependencies {
    api(libs.kotlin.plugin)
    api(libs.vanniktech.publish.plugin)
}

mavenPublishing {
    pom {
        description.set("Internal publishing to Maven Central.")
    }
}