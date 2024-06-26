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