package com.bselzer.gradle.internal.composite.publish.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class CompositePublishPlugin : Plugin<Settings> {
    private companion object {
        const val PUBLISH_GROUP = "publishing"
    }

    override fun apply(settings: Settings) = with(settings) {
        gradle.projectsLoaded {
            if (parent == null) {
                rootProject.addRootTasks()
            } else {
                rootProject.addLeafTasks()
            }
        }
    }

    private fun Project.addRootTasks() {
        // NOTE assumption is that the root project does not actually have any projects itself and purely relies on included builds.
        tasks.register("publishBuildsToMavenCentral") {
            group = PUBLISH_GROUP
            description = "Publishes all projects within the included builds to the Maven Central repository."

            val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenCentral") }
            dependsOn(tasks)
        }

        tasks.register("publishBuildsToMavenLocal") {
            group = PUBLISH_GROUP
            description = "Publishes all projects within the included builds to the Maven local repository."

            val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenLocal") }
            dependsOn(tasks)
        }
    }

    private fun Project.addLeafTasks() {
        tasks.register("publishBuildToMavenCentral") {
            group = PUBLISH_GROUP
            description = "Publishes all projects within this build to the Maven Central repository."

            val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
            dependsOn(tasks)
        }

        tasks.register("publishBuildToMavenLocal") {
            group = PUBLISH_GROUP
            description = "Publishes all projects within this build to the Maven local repository."

            val tasks = getTasksByName("publishToMavenLocal", true)
            dependsOn(tasks)
        }
    }
}