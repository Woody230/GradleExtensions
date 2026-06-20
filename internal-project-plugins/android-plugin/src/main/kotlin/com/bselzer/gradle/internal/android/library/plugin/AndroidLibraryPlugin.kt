package com.bselzer.gradle.internal.android.library.plugin

import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import com.bselzer.gradle.internal.version.catalog.libs
import org.gradle.api.Project

class AndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidLibraryExtension
        get() = androidLibraryExtension

    override fun apply(project: Project) = with(project) {
        pluginManager.apply(libs.plugins.android.application.get().pluginId)
        super.apply(project)
    }
}