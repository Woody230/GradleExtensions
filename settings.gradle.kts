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

includeBuild("common") {
    substitute("io.github.woody230.gradle:multiplatform" to ":multiplatform")
    substitute("io.github.woody230.gradle:properties" to ":properties")
}
includeBuild("internal-common-plugins") {
    substitute("io.github.woody230.gradle:jvm-publish-plugin" to ":jvm-publish-plugin")
    substitute("io.github.woody230.gradle:maven-publish-plugin" to ":maven-publish-plugin")
    substitute("io.github.woody230.gradle:plugin-publish-plugin" to ":plugin-publish-plugin")
}
includeBuild("internal-plugins")