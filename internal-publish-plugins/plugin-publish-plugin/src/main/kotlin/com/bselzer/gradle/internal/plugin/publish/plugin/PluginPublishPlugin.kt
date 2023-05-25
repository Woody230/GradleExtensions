package com.bselzer.gradle.internal.plugin.publish.plugin

import com.bselzer.gradle.function.properties.addOrReplaceProperty
import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.localProperties
import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishPlugin
import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.Platform
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.plugin.devel.PluginDeclaration
import org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin

class PluginPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishPlatform: Platform
        get() = GradlePublishPlugin()

    override val Project.mavenPublishExtension: PluginPublishExtension
        get() = pluginPublishExtension

    override fun apply(project: Project) = with(project) {
        plugins.apply(JavaGradlePluginPlugin::class.java)
        apply(plugin = "com.gradle.plugin-publish")

        super.apply(project)

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
    }

    private val Project.nameConvention: String
        get() {
            // We already know we are publishing a plugin, so it shouldn't exist in the id.
            return name.removeSuffix("-plugin")
        }

    private val Project.displayNameConvention: String
        get() {
            // Example: plugin-publish-plugin should become Plugin Publish Plugin
            return name.split("-").joinToString(separator = " ", transform = String::capitalized)
        }

    private fun NamedDomainObjectContainer<PluginDeclaration>.configurePlugin(
        extension: PluginPublishExtension,
        plugin: com.bselzer.gradle.internal.plugin.publish.plugin.PluginDeclaration,
    ) {
        create(plugin.name.get()) {
            id = "${extension.coordinates.group.get()}.${extension.coordinates.category.get()}.${plugin.name.get()}"
            displayName = plugin.displayName.get()
            description = plugin.description.get()
            implementationClass = plugin.className.get()

            tags.addAll(extension.tags)
            tags.addAll(plugin.tags)
        }
    }

    private fun Project.setupGradleProperties() = with(properties) {
        val localProperties = localProperties

        if (localProperties.containsKeys(LocalProperty.GRADLE_KEY, LocalProperty.GRADLE_SECRET)) {
            addOrReplaceProperty(GradleProperty.GRADLE_KEY, localProperties.getProperty(LocalProperty.GRADLE_KEY))
            addOrReplaceProperty(GradleProperty.GRADLE_SECRET, localProperties.getProperty(LocalProperty.GRADLE_SECRET))
        }
    }
}