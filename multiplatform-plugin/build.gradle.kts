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
    api(projects.androidPlugin)
    api(projects.api)
    api(projects.multiplatform)
    api(libs.compose.gradle.plugin)
    api(libs.android.gradle.plugin)
    api(libs.moko.resources.gradle.plugin)
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}