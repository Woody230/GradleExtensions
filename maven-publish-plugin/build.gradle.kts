plugins {
    alias(libs.plugins.dsl)
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
}