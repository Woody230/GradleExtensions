package com.bselzer.gradle.plugin.publish.plugin

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

interface PluginDeclaration {
    /**
     * The name of the plugin, to be combined with the group and sub-group id
     */
    val name: Property<String>

    /**
     * The friendly name of the plugin.
     */
    val displayName: Property<String>

    val description: Property<String>

    /**
     * The individual tags for this particular plugin, to be combined with the common tags.
     */
    val tags: ListProperty<String>

    /**
     * The package name and class name of the plugin.
     */
    val className: Property<String>
}
