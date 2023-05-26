package com.bselzer.gradle.internal.android.library.plugin

import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import org.gradle.api.Project

class AndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidLibraryExtension
        get() = androidLibraryExtension

    override fun apply(project: Project) = with(project) {
        // TODO libs.plugins.android.library.get().pluginId
        plugins.apply("com.android.library")
        super.apply(project)
    }
}