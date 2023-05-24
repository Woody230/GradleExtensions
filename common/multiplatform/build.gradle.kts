plugins {
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.kotlin.plugin)
}

mavenPublishing {
    pom {
        description.set("Kotlin Multiplatform gradle plugin extensions.")
    }
}