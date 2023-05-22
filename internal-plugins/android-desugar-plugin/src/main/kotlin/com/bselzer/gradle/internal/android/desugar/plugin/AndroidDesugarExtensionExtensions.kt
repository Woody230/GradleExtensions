package com.bselzer.gradle.internal.android.desugar.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME: String = "androidDesugarExtension"

val Project.androidDesugarExtension: AndroidDesugarExtension
    get() = extensions.findByName(EXTENSION_NAME) as? AndroidDesugarExtension ?: extensions.create(EXTENSION_NAME)

fun Project.androidDesugarExtension(configure: AndroidDesugarExtension.() -> Unit) = androidDesugarExtension.apply(configure)