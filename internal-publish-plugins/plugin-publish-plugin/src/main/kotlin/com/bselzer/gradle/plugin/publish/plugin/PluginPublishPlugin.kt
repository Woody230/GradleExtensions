package com.bselzer.gradle.plugin.publish.plugin

import com.bselzer.gradle.maven.publish.plugin.MavenPublishPlugin
import com.bselzer.gradle.maven.publish.plugin.MavenPublishPluginExtension
import com.bselzer.gradle.properties.containsKeys
import com.bselzer.gradle.properties.localProperties
import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.Platform
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.plugin.devel.PluginDeclaration
import org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin

class PluginPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishPlatform: Platform
        get() = GradlePublishPlugin()

    override val Project.mavenPublishExtension: MavenPublishPluginExtension
        get() = pluginPublishExtension

    override fun apply(project: Project) = with(project) {
        super.apply(project)

        plugins.apply(JavaGradlePluginPlugin::class.java)
        apply(plugin = "com.gradle.plugin-publish")

        val extension = pluginPublishExtension {
            tags.convention(emptyList())
        }

        group = "${extension.groupId.get()}.${extension.subGroupId.get()}"
        version = extension.version.get()

        setupGradleProperties()

        with(extensions.getByType<GradlePluginDevelopmentExtension>()) {
            website.set(extension.repository)
            vcsUrl.set("${extension.repository.get()}.git")
            plugins {
                val plugins = extension.plugins.get()
                check(plugins.isNotEmpty()) { "At least one plugin must be published." }

                plugins.forEach { plugin ->
                    plugin.description.convention(mavenPublishExtension.description.get())
                    configurePlugin(extension, plugin)
                }
            }
        }
    }

    private fun NamedDomainObjectContainer<PluginDeclaration>.configurePlugin(
        extension: PluginPublishPluginExtension,
        plugin: com.bselzer.gradle.plugin.publish.plugin.PluginDeclaration,
    ) {
        create(plugin.name.get()) {
            id = "${extension.groupId.get()}.${extension.subGroupId.get()}.${plugin.name.get()}"
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
            setProperty(GradleProperty.GRADLE_KEY, localProperties.getProperty(LocalProperty.GRADLE_KEY))
            setProperty(GradleProperty.GRADLE_SECRET, localProperties.getProperty(LocalProperty.GRADLE_SECRET))
        }
    }
}