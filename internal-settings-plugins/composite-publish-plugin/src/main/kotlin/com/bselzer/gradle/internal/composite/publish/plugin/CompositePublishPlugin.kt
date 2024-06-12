package com.bselzer.gradle.internal.composite.publish.plugin

import com.bselzer.gradle.internal.composite.task.plugin.CompositeTaskPlugin
import org.gradle.api.Project

class CompositePublishPlugin : CompositeTaskPlugin() {
    private companion object {
        const val PUBLISH_INCLUDED_BUILDS_TO_MAVEN_LOCAL = "publishIncludedBuildsToMavenLocal"
        const val PUBLISH_INCLUDED_BUILDS_TO_MAVEN_CENTRAL = "publishIncludedBuildsToMavenCentral"
        const val PUBLISH_RECURSIVELY_TO_MAVEN_LOCAL = "publishRecursivelyToMavenLocal"
        const val PUBLISH_RECURSIVELY_TO_MAVEN_CENTRAL = "publishRecursivelyToMavenCentral"
        const val PUBLISH_ALL_PUBLICATIONS_TO_MAVEN_CENTRAL_REPOSITORY = "publishAllPublicationsToMavenCentralRepository"
        const val PUBLISH_TO_MAVEN_LOCAL = "publishToMavenLocal"
    }

    override fun Project.registerRootTasks() = listOf(
        tasks.register(PUBLISH_INCLUDED_BUILDS_TO_MAVEN_CENTRAL) {
            description = "Publishes all projects within the included builds to the Maven Central repository."
            dependOnIncludedBuilds(":$PUBLISH_RECURSIVELY_TO_MAVEN_CENTRAL")
        },

        tasks.register(PUBLISH_INCLUDED_BUILDS_TO_MAVEN_LOCAL) {
            description = "Publishes all projects within the included builds to the Maven local repository."
            dependOnIncludedBuilds(":$PUBLISH_RECURSIVELY_TO_MAVEN_LOCAL")
        }
    )

    override fun Project.registerLeafTasks() = listOf(
        tasks.register(PUBLISH_RECURSIVELY_TO_MAVEN_CENTRAL) {
            description = "Publishes all projects within this build to the Maven Central repository."
            dependOnRecursively(PUBLISH_ALL_PUBLICATIONS_TO_MAVEN_CENTRAL_REPOSITORY)
        },

        tasks.register(PUBLISH_RECURSIVELY_TO_MAVEN_LOCAL) {
            description = "Publishes all projects within this build to the Maven local repository."
            dependOnRecursively(PUBLISH_TO_MAVEN_LOCAL)
        }
    )
}