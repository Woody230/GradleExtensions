package com.bselzer.gradle.android.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "androidExtension"

val Project.androidExtension: AndroidExtension
    get() = extensions.findByName(EXTENSION_NAME) as? AndroidExtension ?: extensions.create(EXTENSION_NAME)

fun Project.androidExtension(configure: AndroidExtension.() -> Unit) = androidExtension.apply(configure)