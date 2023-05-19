package com.bselzer.gradle.maven.publish.plugin

import org.gradle.api.provider.Property
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomDeveloperSpec

interface MavenPublishExtension {
    /**
     * The base group id of the coordinates.
     */
    val groupId: Property<String>

    /**
     * The specific category, denoting the type of modules.
     */
    val subGroupId: Property<String>

    /**
     * The name of the module.
     */
    val artifactId: Property<String>

    /**
     * The semantic version of the artifact. A snapshot is denoted by a version ending in -SNAPSNOT.
     */
    val version: Property<String>

    /**
     * The url to the repository of the project.
     */
    val repository: Property<String>

    /**
     * A brief description of what the module provides.
     */
    val description: Property<String>

    /**
     * The type of licensing to apply.
     */
    val licensing: Property<Licensing>

    /**
     * Configures the additional developers.
     */
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