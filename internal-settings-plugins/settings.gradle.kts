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

rootProject.name = "internal-settings-plugins"
include("bundled-plugin")
include("composite-build-plugin")
include("composite-property-plugin")
include("composite-publish-plugin")
include("composite-test-plugin")
include("version-catalog-plugin")