package com.bselzer.gradle.maven.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "mavenPublishExtension"

val Project.mavenPublishExtension: MavenPublishPluginExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MavenPublishPluginExtension ?: extensions.create(EXTENSION_NAME)

fun Project.mavenPublishExtension(configure: MavenPublishPluginExtension.() -> Unit) = mavenPublishExtension.apply(configure)