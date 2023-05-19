package com.bselzer.gradle.android.library.desugar.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME: String = "androidLibraryDesugarExtension"

val Project.androidLibraryDesugarExtension: AndroidLibraryDesugarExtension
    get() = extensions.findByName(EXTENSION_NAME) as? AndroidLibraryDesugarExtension ?: extensions.create(EXTENSION_NAME)

fun Project.androidLibraryDesugarExtension(configure: AndroidLibraryDesugarExtension.() -> Unit) = androidLibraryDesugarExtension.apply(configure)