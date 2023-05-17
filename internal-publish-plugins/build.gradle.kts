allprojects {
    group = "io.github.woody230.gradle"
    version = "1.0.0"

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks.register("publishBuildToMavenLocal") {
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}