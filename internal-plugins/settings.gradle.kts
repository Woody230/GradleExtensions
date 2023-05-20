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

rootProject.name = "internal-plugins"
include("android-desugar-plugin")
include("android-plugin")
include("multiplatform-compose-plugin")
include("multiplatform-compose-test-plugin")
include("multiplatform-plugin")
include("multiplatform-publish-plugin")
include("multiplatform-resource-plugin")
include("multiplatform-test-plugin")