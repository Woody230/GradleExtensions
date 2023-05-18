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

include("android-desugar-plugin")
include("android-plugin")
include("multiplatform-compose-plugin")
include("multiplatform-plugin")
include("multiplatform-publish-plugin")
include("multiplatform-resource-plugin")