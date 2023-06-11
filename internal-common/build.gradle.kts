allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishBuildToMavenCentral") {
    group = "publishing"
    val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
    dependsOn(tasks)
}

tasks.register("publishBuildToMavenLocal") {
    group = "publishing"
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}