package com.bselzer.gradle.internal.android.kotlin.multiplatform.library.plugin

import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Project

class MultiplatformAndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: MultiplatformAndroidLibraryExtension
        get() = multiplatformAndroidLibraryExtension

    override fun apply(project: Project) = with(project) {
        pluginManager.apply(BuildConfiguration.plugins_android_kotlin_multiplatform_library)
        super.apply(project)
    }
}
