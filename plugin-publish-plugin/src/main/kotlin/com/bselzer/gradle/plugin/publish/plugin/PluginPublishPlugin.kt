package com.bselzer.gradle.plugin.publish.plugin

import com.bselzer.gradle.api.containsKeys
import com.bselzer.gradle.api.localProperties
import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishBasePlugin
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.plugin.devel.PluginDeclaration

class PluginPublishPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(MavenPublishBasePlugin::class.java)

        val extension = pluginPublishExtension {
            groupId.convention("io.github.woody230")
            tags.convention(emptyList())
        }

        group = "${extension.groupId}.${extension.subGroupId}"
        version = extension.version

        setupGradleProperties()

        with(extensions.getByType<GradlePluginDevelopmentExtension>()) {
            website.set(extension.repository)
            vcsUrl.set("${extension.repository}.git")
            plugins {
                extension.plugins.get().forEach { plugin ->
                    configurePlugin(extension, plugin)
                }
            }
        }

        with(extensions.getByType<MavenPublishBaseExtension>()) {
            configurePlatform()
        }
    }

    private fun NamedDomainObjectContainer<PluginDeclaration>.configurePlugin(
        extension: PluginPublishPluginExtension,
        plugin: com.bselzer.gradle.plugin.publish.plugin.PluginDeclaration,
    ) {
        plugin.displayName.convention(plugin.name)

        create(plugin.name.get()) {
            id = "${extension.groupId}.${extension.subGroupId}.${plugin.name}"
            displayName = plugin.displayName.get()
            description = plugin.description.get()
            implementationClass = plugin.className.get()

            tags.addAll(extension.tags)
            tags.addAll(plugin.tags)
        }
    }

    private fun MavenPublishBaseExtension.configurePlatform() {
        val platform = GradlePublishPlugin()
        configure(platform)
    }

    private fun Project.setupGradleProperties() = with(properties) {
        val localProperties = localProperties

        if (localProperties.containsKeys(LocalProperty.GRADLE_KEY, LocalProperty.GRADLE_SECRET)) {
            setProperty(GradleProperty.GRADLE_KEY, localProperties.getProperty(LocalProperty.GRADLE_KEY))
            setProperty(GradleProperty.GRADLE_SECRET, localProperties.getProperty(LocalProperty.GRADLE_SECRET))
        }
    }
}