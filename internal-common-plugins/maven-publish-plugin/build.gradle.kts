plugins {
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(libs.woody230.gradle.properties)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
}