package com.bselzer.gradle.multiplatform.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "multiplatformExtension"

val Project.multiplatformExtension: MultiplatformExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MultiplatformExtension ?: extensions.create(EXTENSION_NAME)

fun Project.multiplatformExtension(configure: MultiplatformExtension.() -> Unit) = multiplatformExtension.apply(configure)