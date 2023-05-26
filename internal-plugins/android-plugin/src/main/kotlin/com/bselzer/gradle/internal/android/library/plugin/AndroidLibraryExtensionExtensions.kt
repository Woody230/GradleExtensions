package com.bselzer.gradle.internal.android.library.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "androidLibraryExtension"

val Project.androidLibraryExtension: AndroidLibraryExtension
    get() = extensions.findByName(EXTENSION_NAME) as? AndroidLibraryExtension ?: extensions.create(EXTENSION_NAME)

fun Project.androidLibraryExtension(configure: AndroidLibraryExtension.() -> Unit) = androidLibraryExtension.apply(configure)