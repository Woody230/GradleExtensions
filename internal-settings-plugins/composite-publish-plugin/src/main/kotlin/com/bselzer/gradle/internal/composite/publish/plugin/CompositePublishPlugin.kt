package com.bselzer.gradle.internal.composite.publish.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class CompositePublishPlugin : Plugin<Settings> {
    private companion object {
        const val GROUP = "composite"
        const val PUBLISH_BUILDS_TO_MAVEN_LOCAL = "publishBuildsToMavenLocal"
        const val PUBLISH_BUILDS_TO_MAVEN_CENTRAL = "publishBuildsToMavenCentral"
        const val PUBLISH_BUILD_TO_MAVEN_LOCAL = "publishBuildToMavenLocal"
        const val PUBLISH_BUILD_TO_MAVEN_CENTRAL = "publishBuildToMavenCentral"
        const val PUBLISH_ALL_PUBLICATIONS_TO_MAVEN_CENTRAL_REPOSITORY = "publishAllPublicationsToMavenCentralRepository"
        const val PUBLISH_TO_MAVEN_LOCAL = "publishToMavenLocal"
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
        tasks.register(PUBLISH_BUILDS_TO_MAVEN_CENTRAL) {
            group = GROUP
            description = "Publishes all projects within the included builds to the Maven Central repository."

            val tasks = gradle.includedBuilds.map { build -> build.task(":${PUBLISH_BUILD_TO_MAVEN_CENTRAL}") }
            dependsOn(tasks)
        }

        tasks.register(PUBLISH_BUILDS_TO_MAVEN_LOCAL) {
            group = GROUP
            description = "Publishes all projects within the included builds to the Maven local repository."

            val tasks = gradle.includedBuilds.map { build -> build.task(":${PUBLISH_BUILD_TO_MAVEN_LOCAL}") }
            dependsOn(tasks)
        }
    }

    private fun Project.addLeafTasks() {
        tasks.register(PUBLISH_BUILD_TO_MAVEN_CENTRAL) {
            group = GROUP
            description = "Publishes all projects within this build to the Maven Central repository."

            val tasks = getTasksByName(PUBLISH_ALL_PUBLICATIONS_TO_MAVEN_CENTRAL_REPOSITORY, true)
            dependsOn(tasks)
        }

        tasks.register(PUBLISH_BUILD_TO_MAVEN_LOCAL) {
            group = GROUP
            description = "Publishes all projects within this build to the Maven local repository."

            val tasks = getTasksByName(PUBLISH_TO_MAVEN_LOCAL, true)
            dependsOn(tasks)
        }
    }
}