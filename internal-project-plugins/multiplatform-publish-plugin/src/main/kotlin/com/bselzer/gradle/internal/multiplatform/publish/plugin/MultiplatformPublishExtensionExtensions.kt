package com.bselzer.gradle.internal.multiplatform.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "multiplatformPublishExtension"

val Project.multiplatformPublishExtension: MultiplatformPublishExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MultiplatformPublishExtension ?: extensions.create(EXTENSION_NAME)

fun Project.multiplatformPublishExtension(configure: MultiplatformPublishExtension.() -> Unit) = multiplatformPublishExtension.apply(configure)