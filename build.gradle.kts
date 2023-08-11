allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishBuildsToMavenCentral") {
    group = "composite"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenCentral") }
    dependsOn(tasks)
}

tasks.register("publishBuildsToMavenLocal") {
    group = "composite"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenLocal") }
    dependsOn(tasks)
}