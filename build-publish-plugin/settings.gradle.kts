pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

// TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "build-publish-plugin"
include("api")
include("maven-publish-plugin")
include("plugin-publish-plugin")
project(":api").projectDir = File("../api")
project(":maven-publish-plugin").projectDir = File("../maven-publish-plugin")
project(":plugin-publish-plugin").projectDir = File("../plugin-publish-plugin")
