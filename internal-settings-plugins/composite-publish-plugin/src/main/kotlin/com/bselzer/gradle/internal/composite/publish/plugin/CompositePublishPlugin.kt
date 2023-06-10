package com.bselzer.gradle.internal.composite.publish.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class CompositePublishPlugin : Plugin<Settings> {
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
        tasks.register("publishBuildsToMavenCentralRepository") {
            val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenCentralRepository") }
            dependsOn(tasks)
        }

        tasks.register("publishBuildsToMavenLocal") {
            val tasks = gradle.includedBuilds.map { build -> build.task(":publishBuildToMavenLocal") }
            dependsOn(tasks)
        }
    }

    private fun Project.addLeafTasks() {
        tasks.register("publishBuildToMavenCentralRepository") {
            val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
            dependsOn(tasks)
        }

        tasks.register("publishBuildToMavenLocal") {
            val tasks = getTasksByName("publishToMavenLocal", true)
            dependsOn(tasks)
        }
    }
}