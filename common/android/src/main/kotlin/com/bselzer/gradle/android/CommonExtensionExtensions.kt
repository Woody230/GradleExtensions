package com.bselzer.gradle.android

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

val Project.androidExtension: CommonExtension<*, *, *, *, *>
    get() = androidExtensionOrNull ?: throw NotImplementedError("Unable to find a CommonExtension. The Android application or library plugin must be configured.")

val Project.androidExtensionOrNull: CommonExtension<*, *, *, *, *>?
    get() = when {
        // TODO libs.plugins.android.application.get().pluginId
        pluginManager.hasPlugin("com.android.application") -> extensions.getByType(BaseAppModuleExtension::class.java)

        // TODO libs.plugins.android.library.get().pluginId
        pluginManager.hasPlugin("com.android.library") -> extensions.getByType(LibraryExtension::class.java)
        else -> null
    }