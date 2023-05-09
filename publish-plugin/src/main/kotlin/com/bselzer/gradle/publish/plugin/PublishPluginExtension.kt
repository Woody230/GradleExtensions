package com.bselzer.gradle.publish.plugin

import org.gradle.api.provider.Property
import org.gradle.api.publish.maven.MavenPomDeveloper
import org.gradle.api.publish.maven.MavenPomDeveloperSpec

interface PublishPluginExtension {
    val groupId: Property<String>
    val subGroupId: Property<String>
    val artifactId: Property<String>
    val version: Property<String>
    val repository: Property<String>

    val description: Property<String>
    val devs: Property<MavenPomDeveloperSpec.() -> Unit>

    /**
     * Adds a developer to the [MavenPomDeveloperSpec].
     */
    fun developer(configure: MavenPomDeveloper.() -> Unit) {
        val current = devs.get()
        devs.set {
            current()
            developer(configure)
        }
    }
}