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
        classpath(libs.woody230.gradle.properties)
    }
}