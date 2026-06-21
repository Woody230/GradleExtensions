plugins {
    id(libs.plugins.woody230.gradle.convention.plugin.get().pluginId)
}

dependencies {
    api(libs.kotlin.plugin)
    api(libs.vanniktech.publish.plugin)
    api(libs.woody230.gradle.function)
    api(libs.woody230.gradle.internal.models)
    implementation(libs.woody230.gradle.internal.named.version.catalog)
}

mavenPublishing {
    pom {
        description.set("Internal publishing to Maven Central.")
    }
}