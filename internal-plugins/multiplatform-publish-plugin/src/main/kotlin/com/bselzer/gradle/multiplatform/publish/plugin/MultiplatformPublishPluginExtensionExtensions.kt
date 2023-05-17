package com.bselzer.gradle.multiplatform.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "multiplatformPublishExtension"

val Project.multiplatformPublishExtension: MultiplatformPublishPluginExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MultiplatformPublishPluginExtension ?: extensions.create(EXTENSION_NAME)

fun Project.multiplatformPublishExtension(configure: MultiplatformPublishPluginExtension.() -> Unit) = multiplatformPublishExtension.apply(configure)