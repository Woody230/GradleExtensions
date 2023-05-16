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

include("api")
project(":api").projectDir = File("build-common/api")
include("maven-publish-plugin")
project(":maven-publish-plugin").projectDir = File("build-common/maven-publish-plugin")
include("plugin-publish-plugin")
project(":plugin-publish-plugin").projectDir = File("build-common/plugin-publish-plugin")

include("android-desugar-plugin")
project(":android-desugar-plugin").projectDir = File("build-plugins/android-desugar-plugin")
include("android-plugin")
include("multiplatform")
include("multiplatform-compose-plugin")
include("multiplatform-plugin")
include("multiplatform-publish-plugin")
include("multiplatform-resource-plugin")

includeBuild("build-common") {
    dependencySubstitution {
        val module = module("io.github.woody230.gradle:plugin-publish-plugin")
        val project = project(":plugin-publish-plugin")
        substitute(module).using(project)
    }
}

includeBuild("build-plugins")