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

fun ConfigurableIncludedBuild.substituteModulesUsingProjects(
    vararg substitutions: Pair<String, String>
): Unit = dependencySubstitution {
    substitutions.forEach { (moduleNotation, projectNotation) ->
        val module = module(moduleNotation)
        val project = project(projectNotation)
        substitute(module).using(project)
    }
}

includeBuild("convention-plugins")
includeBuild("internal-common") {
    substituteModulesUsingProjects(
        "io.github.woody230.gradle.internal:composite-task" to ":composite-task",
        "io.github.woody230.gradle.internal:models" to ":models"
    )
}
includeBuild("internal-publish-plugins") {
    substituteModulesUsingProjects(
        "io.github.woody230.gradle.internal:jvm-publish-plugin" to ":jvm-publish-plugin",
        "io.github.woody230.gradle.internal:maven-publish-plugin" to ":maven-publish-plugin",
        "io.github.woody230.gradle.internal:plugin-publish-plugin" to ":plugin-publish-plugin"
    )
}
includeBuild("common") {
    substituteModulesUsingProjects(
        "io.github.woody230.gradle:android" to ":android",
        "io.github.woody230.gradle:function" to ":function",
        "io.github.woody230.gradle:multiplatform" to ":multiplatform"
    )
}
includeBuild("internal-project-plugins")
includeBuild("internal-settings-plugins")