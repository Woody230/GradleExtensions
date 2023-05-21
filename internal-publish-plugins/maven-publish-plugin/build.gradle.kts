dependencies {
    api(libs.kotlin.plugin)
    api(libs.vanniktech.publish.plugin)
    api(libs.woody230.gradle.internal.models)
}

mavenPublishing {
    pom {
        description.set("Internal publishing to Maven Central.")
    }
}