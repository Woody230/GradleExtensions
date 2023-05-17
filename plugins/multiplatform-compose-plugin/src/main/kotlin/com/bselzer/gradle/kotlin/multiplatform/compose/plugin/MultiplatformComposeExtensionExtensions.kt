package com.bselzer.gradle.kotlin.multiplatform.compose.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "multiplatformComposeExtension"

val Project.multiplatformComposeExtension: MultiplatformComposeExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MultiplatformComposeExtension ?: extensions.create(EXTENSION_NAME)

fun Project.multiplatformComposeExtension(configure: MultiplatformComposeExtension.() -> Unit) = multiplatformComposeExtension.apply(configure)