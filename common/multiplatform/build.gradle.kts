plugins {
    `kotlin-dsl`
}

dependencies {
    api(gradleApi())
    api(libs.kotlin.gradle.plugin)
    api(libs.kotlin.multiplatform.gradle.plugin)
}