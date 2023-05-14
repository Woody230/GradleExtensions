package com.bselzer.gradle.kotlin.multiplatform.publish.plugin

import org.gradle.api.provider.Property
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomDeveloperSpec

interface MultiplatformPublishPluginExtension {
    val groupId: Property<String>
    val subGroupId: Property<String>
    val artifactId: Property<String>
    val version: Property<String>

    /**
     * The url to the repository of the project.
     */
    val repository: Property<String>

    val description: Property<String>

    val developers: Property<MavenPomDeveloperSpec.() -> Unit>

    /**
     * Adds a developer to the [MavenPomDeveloperSpec].
     */
    fun developer(configure: MavenPomDeveloper.() -> Unit) {
        val current = developers.get()
        developers.set {
            current()
            developer(configure)
        }
    }
}