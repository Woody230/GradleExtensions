package com.bselzer.gradle.android.application.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "androidApplicationExtension"

val Project.androidApplicationExtension: AndroidApplicationExtension
    get() = extensions.findByName(EXTENSION_NAME) as? AndroidApplicationExtension ?: extensions.create(EXTENSION_NAME)

fun Project.androidApplicationExtension(configure: AndroidApplicationExtension.() -> Unit) = androidApplicationExtension.apply(configure)