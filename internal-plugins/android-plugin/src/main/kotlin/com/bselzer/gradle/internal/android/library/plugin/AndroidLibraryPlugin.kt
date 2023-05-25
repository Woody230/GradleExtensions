package com.bselzer.gradle.internal.android.library.plugin

import com.android.build.gradle.LibraryPlugin
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import org.gradle.api.Project

class AndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidLibraryExtension
        get() = androidLibraryExtension

    override fun apply(project: Project) = with(project) {
        plugins.apply(LibraryPlugin::class.java)
        super.apply(project)
    }
}