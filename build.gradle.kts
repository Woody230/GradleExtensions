allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishIncludedBuildsToMavenCentral") {
    group = "composite"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishRecursivelyToMavenCentral") }
    dependsOn(tasks)
}

tasks.register("publishIncludedBuildsToMavenLocal") {
    group = "composite"
    val tasks = gradle.includedBuilds.map { build -> build.task(":publishRecursivelyToMavenLocal") }
    dependsOn(tasks)
}