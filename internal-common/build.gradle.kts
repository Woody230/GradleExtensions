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

    dependencies {
        classpath(libs.kotlin.plugin)
    }
}

// TODO must use root project: extension libs does not exist https://github.com/gradle/gradle/issues/18237
subprojects {
    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)

    // TODO can't apply publish convention plugin, must be done in plugins block in each individual build.gradle
}

tasks.register("publishBuildToMavenLocal") {
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}