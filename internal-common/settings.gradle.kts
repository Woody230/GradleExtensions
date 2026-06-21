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

rootProject.name = "internal-common"
include("composite-task")
include("models")
include("named-version-catalog")