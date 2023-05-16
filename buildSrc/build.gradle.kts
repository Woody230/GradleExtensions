plugins {
    alias(libs.plugins.dsl)
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.moko.resources.generator)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.woody230.gradle.plugin.publish)
    implementation(libs.publish.gradle.plugin)

    // TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}