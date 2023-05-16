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

includeBuild("build-common") {
    val substitutions = mapOf(
        "io.github.woody230.gradle:api" to ":api",
        "io.github.woody230.gradle:maven-publish-plugin" to ":maven-publish-plugin",
        "io.github.woody230.gradle:multiplatform" to ":multiplatform",
        "io.github.woody230.gradle:plugin-publish-plugin" to ":plugin-publish-plugin",
    )

    for (substitution in substitutions) {
        dependencySubstitution {
            val module = module(substitution.key)
            val project = project(substitution.value)
            substitute(module).using(project)
        }
    }
}
includeBuild("build-plugins")