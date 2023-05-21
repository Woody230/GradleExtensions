package com.bselzer.gradle.internal.maven.publish.plugin

import com.bselzer.gradle.internal.models.ModuleId
import org.gradle.api.provider.Property
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.tasks.Nested

interface MavenPublishExtension {
    /**
     * The id of the coordinates.
     */
    @get:Nested
    val coordinates: ModuleId

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