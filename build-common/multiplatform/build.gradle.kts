plugins {
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.kotlin.gradle.plugin)
    api(libs.kotlin.multiplatform.gradle.plugin)
}