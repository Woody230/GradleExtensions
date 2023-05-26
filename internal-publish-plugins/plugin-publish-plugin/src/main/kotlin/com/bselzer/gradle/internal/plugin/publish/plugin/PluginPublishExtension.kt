package com.bselzer.gradle.internal.plugin.publish.plugin

import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishExtension
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.kotlin.dsl.newInstance

interface PluginPublishExtension : MavenPublishExtension {
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
        this@PluginPublishExtension.plugins.add(plugin)
    }
}