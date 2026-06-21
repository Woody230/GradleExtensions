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

rootProject.name = "internal-common"
include("build-configuration")
include("composite-task")
include("models")
include("named-version-catalog")