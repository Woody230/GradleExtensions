plugins {
    alias(libs.plugins.jvm)
}

buildscript {
    dependencies {
        classpath(libs.moko.resources.generator)
    }
}

dependencies {
    api(projects.api)
    api(projects.multiplatform)
    api(libs.android.gradle.plugin)
    api(libs.moko.resources.gradle.plugin)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)
}