package com.bselzer.gradle.android

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

val Project.androidExtension: CommonExtension<*, *, *, *>
    get() = androidExtensionOrNull ?: throw NotImplementedError("Unable to find a CommonExtension. The Android application or library plugin must be configured.")

val Project.androidExtensionOrNull: CommonExtension<*, *, *, *>?
    get() = when {
        plugins.hasPlugin(libs.plugins.android.application.get().pluginId) -> extensions.getByType(BaseAppModuleExtension::class.java)
        plugins.hasPlugin(libs.plugins.android.library.get().pluginId) -> extensions.getByType(LibraryExtension::class.java)
        else -> null
    }