allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishBuildsToMavenLocal") {
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenLocal") }
    dependsOn(tasks)
}