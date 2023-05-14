package com.bselzer.gradle.plugin.publish.plugin

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.newInstance

interface PluginPublishPluginExtension {
    val groupId: Property<String>
    val subGroupId: Property<String>
    val version: Property<String>

    /**
     * The url to the repository of the project.
     */
    val repository: Property<String>

    /**
     * Common tags to apply to all plugins.
     */
    val tags: ListProperty<String>

    /**
     * The plugins to publish.
     */
    val plugins: ListProperty<PluginDeclaration>

    /**
     * Adds a plugin to the list of [plugins].
     */
    fun Project.plugin(configure: PluginDeclaration.() -> Unit) {
        val plugin = objects.newInstance<PluginDeclaration>().apply(configure)
        this@PluginPublishPluginExtension.plugins.add(plugin)
    }
}