package com.bselzer.gradle.internal.android.library.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.bselzer.gradle.internal.android.plugin.AndroidExtension
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidExtension
        get() = androidLibraryExtension

    override val Project.commonExtension: CommonExtension<*, *, *, *>
        get() = extensions.getByType<LibraryExtension>()

    override fun apply(project: Project) = with(project) {
        plugins.apply(LibraryPlugin::class.java)
        super.apply(project)
    }
}