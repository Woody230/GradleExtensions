allprojects {
    group = "io.github.woody230.gradle"
    version = "1.0.0"

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.publish.gradle.plugin)
        classpath(libs.woody230.gradle.api)
        classpath(libs.woody230.gradle.maven.publish)
        classpath(libs.woody230.gradle.multiplatform)
        classpath(libs.woody230.gradle.plugin.publish)
    }
}