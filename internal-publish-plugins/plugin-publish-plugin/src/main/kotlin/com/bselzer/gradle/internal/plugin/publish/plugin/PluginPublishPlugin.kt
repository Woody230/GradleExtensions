package com.bselzer.gradle.internal.plugin.publish.plugin

import com.bselzer.gradle.function.properties.addOrReplaceProperty
import com.bselzer.gradle.function.properties.compositeLocalProperties
import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.injectLocalProperty
import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishPlugin
import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.Platform
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.plugin.devel.PluginDeclaration

class PluginPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishPlatform: Platform
        get() = GradlePublishPlugin()

    override val Project.mavenPublishExtension: PluginPublishExtension
        get() = pluginPublishExtension

    override fun apply(project: Project) = with(project) {
        setupGradleProperties()

        val extension = pluginPublishExtension {
            tags.convention(emptyList())
        }

        afterEvaluate {
            group = "${extension.coordinates.group.get()}.${extension.coordinates.category.get()}"
            version = extension.version.get()

            with(extensions.getByType<GradlePluginDevelopmentExtension>()) {
                website.set(extension.repository)
                vcsUrl.set("${extension.repository.get()}.git")
                plugins {
                    val plugins = extension.plugins.get()
                    check(plugins.isNotEmpty()) { "[$name] At least one plugin must be published." }

                    plugins.forEach { plugin ->
                        // If there is a single plugin, then we can make assumptions about the name/description because the module is dedicated.
                        if (plugins.size == 1) {
                            plugin.description.convention(mavenPublishExtension.description.get())
                            plugin.name.convention(nameConvention)
                            plugin.displayName.convention(displayNameConvention)
                        }

                        configurePlugin(extension, plugin)
                    }
                }
            }
        }

        // TODO libs.plugins.java.get().pluginId
        apply(plugin = "org.gradle.java-gradle-plugin")

        // TODO libs.plugins.gradle.publish.get().pluginId
        apply(plugin = "com.gradle.plugin-publish")

        super.apply(project)
    }

    private val Project.nameConvention: String
        get() {
            // We already know we are publishing a plugin, so it shouldn't exist in the id.
            return name.removeSuffix("-plugin")
        }

    private val Project.displayNameConvention: String
        get() {
            // Example: plugin-publish-plugin should become Plugin Publish Plugin
            return name.split("-").joinToString(separator = " ") { component ->
                component.replaceFirstChar(Char::uppercase)
            }
        }

    private fun NamedDomainObjectContainer<PluginDeclaration>.configurePlugin(
        extension: PluginPublishExtension,
        plugin: com.bselzer.gradle.internal.plugin.publish.plugin.PluginDeclaration,
    ) {
        val id = "${extension.coordinates.group.get()}.${extension.coordinates.category.get()}.${plugin.name.get()}"
        create(id) {
            this.id = id
            displayName = plugin.displayName.get()
            description = plugin.description.get()
            implementationClass = plugin.className.get()

            tags.addAll(extension.tags)
            tags.addAll(plugin.tags)
        }
    }

    private fun Project.setupGradleProperties() {
        injectLocalProperty(LocalProperty.GRADLE_KEY, GradleProperty.GRADLE_KEY)
        injectLocalProperty(LocalProperty.GRADLE_SECRET, GradleProperty.GRADLE_SECRET)
    }
}