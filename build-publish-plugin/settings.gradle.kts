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

include("api")
include("maven-publish-plugin")
include("plugin-publish-plugin")
project(":api").projectDir = File("../api")
project(":maven-publish-plugin").projectDir = File("../maven-publish-plugin")
project(":plugin-publish-plugin").projectDir = File("../plugin-publish-plugin")
