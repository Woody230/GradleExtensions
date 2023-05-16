plugins {
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(projects.api)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
}