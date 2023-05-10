plugins {
    `kotlin-dsl`
    alias(libs.plugins.jvm)
}

buildscript {
    dependencies {
        classpath(libs.moko.resources.generator)
    }
}

dependencies {
    api(projects.api)
    api(libs.moko.resources.gradle.plugin)
    implementation(projects.androidPlugin)
    implementation(projects.multiplatformPublishPlugin)
}