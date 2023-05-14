package com.bselzer.gradle.plugin.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "pluginPublishExtension"

val Project.pluginPublishExtension: PluginPublishPluginExtension
    get() = extensions.findByName(EXTENSION_NAME) as? PluginPublishPluginExtension ?: extensions.create(EXTENSION_NAME)

fun Project.pluginPublishExtension(configure: PluginPublishPluginExtension.() -> Unit) = pluginPublishExtension.apply(configure)