allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishBuildsToMavenCentralRepository") {
    group = "publishing"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenCentralRepository") }
    dependsOn(tasks)
}

tasks.register("publishBuildsToMavenLocal") {
    group = "publishing"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenLocal") }
    dependsOn(tasks)
}