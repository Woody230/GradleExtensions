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

rootProject.name = "internal-settings-plugins"
include("bundled-plugin")
include("composite-build-plugin")
include("composite-property-plugin")
include("composite-publish-plugin")
include("composite-test-plugin")
include("version-catalog-plugin")