plugins {
    alias(libs.plugins.jvm)
}

dependencies {
    api(gradleApi())
    api(projects.api)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)
}