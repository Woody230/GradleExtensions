package com.bselzer.gradle.jvm.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "jvmPublishExtension"

val Project.jvmPublishExtension: JvmPublishPluginExtension
    get() = extensions.findByName(EXTENSION_NAME) as? JvmPublishPluginExtension ?: extensions.create(EXTENSION_NAME)

fun Project.jvmPublishExtension(configure: JvmPublishPluginExtension.() -> Unit) = jvmPublishExtension.apply(configure)