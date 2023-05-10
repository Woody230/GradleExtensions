package com.bselzer.gradle.android.desugar.plugin

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.bselzer.gradle.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidDesugarPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(LibraryPlugin::class.java)

        with(extensions.getByType<LibraryExtension>()) {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
            project.dependencies {
                add("coreLibraryDesugaring", libs.android.desugar.get())
            }
        }
    }
}