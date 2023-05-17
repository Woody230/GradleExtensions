package com.bselzer.gradle.maven.publish.plugin

import org.gradle.api.provider.Property
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomDeveloperSpec

interface MavenPublishPluginExtension {
    val groupId: Property<String>
    val subGroupId: Property<String>
    val artifactId: Property<String>
    val version: Property<String>

    /**
     * The url to the repository of the project.
     */
    val repository: Property<String>

    val description: Property<String>

    /**
     * The type of licensing to apply.
     */
    val licensing: Property<Licensing>

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