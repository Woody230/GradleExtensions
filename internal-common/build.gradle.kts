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
    group = "composite"
    val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
    dependsOn(tasks)
}

tasks.register("publishBuildToMavenLocal") {
    group = "composite"
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}