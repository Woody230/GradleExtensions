pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    val path = "../gradle/libs.versions.toml"
    versionCatalogs {
        create("libs") {
            from(files(path))
        }
        create("ioGithubWoody230GradleInternal") {
            from(files(path))
        }
    }
}

rootProject.name = "internal-publish-plugins"
include("jvm-publish-plugin")
include("maven-publish-plugin")
include("plugin-publish-plugin")
include("version-catalog-publish-plugin")