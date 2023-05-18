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

rootProject.name = "internal-common-plugins"
include("jvm-publish-plugin")
include("maven-publish-plugin")
include("plugin-publish-plugin")
include("settings-plugin")