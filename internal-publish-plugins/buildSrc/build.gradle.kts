plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.publish.gradle.plugin)
    implementation(libs.woody230.gradle.properties)
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.vanniktech.publish.gradle.plugin)

    // TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}