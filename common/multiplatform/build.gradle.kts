plugins {
    id(libs.plugins.woody230.gradle.convention.jvm.get().pluginId)
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