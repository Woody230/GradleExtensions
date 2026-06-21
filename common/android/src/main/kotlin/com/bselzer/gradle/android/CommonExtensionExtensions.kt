package com.bselzer.gradle.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Project

val Project.androidExtension: CommonExtension
    get() = androidExtensionOrNull ?: throw NotImplementedError("Unable to find a CommonExtension. The Android application or library plugin must be configured.")

val Project.androidExtensionOrNull: CommonExtension?
    get() = when {
        pluginManager.hasPlugin(BuildConfiguration.plugins_android_application) -> extensions.getByType(ApplicationExtension::class.java)
        pluginManager.hasPlugin(BuildConfiguration.plugins_android_library) -> extensions.getByType(LibraryExtension::class.java)
        else -> null
    }
