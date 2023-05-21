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

fun ConfigurableIncludedBuild.substitute(substitution: Pair<String, String>) {
    dependencySubstitution {
        val module = module(substitution.first)
        val project = project(substitution.second)
        substitute(module).using(project)
    }
}

includeBuild("internal-common") {
    substitute("io.github.woody230.gradle.internal:models" to ":models")
}
includeBuild("internal-publish-plugins") {
    substitute("io.github.woody230.gradle.internal:jvm-publish-plugin" to ":jvm-publish-plugin")
    substitute("io.github.woody230.gradle.internal:maven-publish-plugin" to ":maven-publish-plugin")
    substitute("io.github.woody230.gradle.internal:plugin-publish-plugin" to ":plugin-publish-plugin")
}
includeBuild("common") {
    substitute("io.github.woody230.gradle:android" to ":android")
    substitute("io.github.woody230.gradle:multiplatform" to ":multiplatform")
    substitute("io.github.woody230.gradle:properties" to ":properties")
}
includeBuild("internal-plugins")