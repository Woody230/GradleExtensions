plugins {
    `kotlin-dsl`
    alias(libs.plugins.jvm)
}

dependencies {
    api(gradleApi())
    api(libs.kotlin.gradle.plugin)
    api(libs.kotlin.multiplatform.gradle.plugin)
}