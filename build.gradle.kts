allprojects {
    group = "io.github.woody230.gradle"
    version = "1.0.0"

    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.kotlin.dsl.gradle.plugin)
        classpath(libs.moko.resources.generator)
    }
}

subprojects {
    apply(plugin = rootProject.libs.plugins.dsl.get().pluginId)
    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)
}