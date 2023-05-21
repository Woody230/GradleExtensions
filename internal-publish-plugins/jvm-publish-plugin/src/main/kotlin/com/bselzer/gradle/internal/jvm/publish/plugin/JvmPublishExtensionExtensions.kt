package com.bselzer.gradle.internal.jvm.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "jvmPublishExtension"

val Project.jvmPublishExtension: JvmPublishExtension
    get() = extensions.findByName(EXTENSION_NAME) as? JvmPublishExtension ?: extensions.create(EXTENSION_NAME)

fun Project.jvmPublishExtension(configure: JvmPublishExtension.() -> Unit) = jvmPublishExtension.apply(configure)