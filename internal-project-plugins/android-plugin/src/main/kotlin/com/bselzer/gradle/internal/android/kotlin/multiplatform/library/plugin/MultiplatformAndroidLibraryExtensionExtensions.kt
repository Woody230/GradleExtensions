package com.bselzer.gradle.internal.android.kotlin.multiplatform.library.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "multiplatformAndroidLibraryExtension"

val Project.multiplatformAndroidLibraryExtension: MultiplatformAndroidLibraryExtension
    get() = extensions.findByName(EXTENSION_NAME) as? MultiplatformAndroidLibraryExtension ?: extensions.create(EXTENSION_NAME)

fun Project.multiplatformAndroidLibraryExtension(configure: MultiplatformAndroidLibraryExtension.() -> Unit) = multiplatformAndroidLibraryExtension.apply(configure)