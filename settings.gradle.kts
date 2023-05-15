pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

// TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "GradleExtensions"
include("android-desugar-plugin")
include("android-plugin")
include("api")
include("maven-publish-plugin")
include("multiplatform")
include("multiplatform-compose-plugin")
include("multiplatform-plugin")
include("multiplatform-publish-plugin")
include("multiplatform-resource-plugin")
include("plugin-publish-plugin")

includeBuild("build-publish-plugin") {
    dependencySubstitution {
        val module = module("io.github.woody230.gradle:plugin-publish-plugin")
        val project = project(":plugin-publish-plugin")
        substitute(module).using(project)
    }
}