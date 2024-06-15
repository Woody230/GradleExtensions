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

tasks.register("publishRecursivelyToMavenCentral") {
    group = "composite"
    val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
    dependsOn(tasks)
}

tasks.register("publishRecursivelyToMavenLocal") {
    group = "composite"
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}