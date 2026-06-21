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
        create("ioGithubWoody230GradleInternalLibs") {
            from(files(path))
        }
    }
}

rootProject.name = "internal-project-plugins"
include("aboutlibraries-plugin")
include("android-desugar-plugin")
include("android-plugin")
include("buildkonfig-plugin")
include("multiplatform-compose-plugin")
include("multiplatform-compose-test-plugin")
include("multiplatform-plugin")
include("multiplatform-publish-plugin")
include("multiplatform-test-plugin")