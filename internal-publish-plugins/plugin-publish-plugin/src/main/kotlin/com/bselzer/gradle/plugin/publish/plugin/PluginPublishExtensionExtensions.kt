package com.bselzer.gradle.plugin.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "pluginPublishExtension"

val Project.pluginPublishExtension: PluginPublishExtension
    get() = extensions.findByName(EXTENSION_NAME) as? PluginPublishExtension ?: extensions.create(EXTENSION_NAME)

fun Project.pluginPublishExtension(configure: PluginPublishExtension.() -> Unit) = pluginPublishExtension.apply(configure)