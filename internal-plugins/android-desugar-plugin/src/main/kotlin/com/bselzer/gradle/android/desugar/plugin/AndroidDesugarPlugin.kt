package com.bselzer.gradle.android.desugar.plugin

import com.bselzer.gradle.android.androidExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDesugarPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val extension = androidDesugarExtension
        dependencies {

            val dependency: Any = when {
                extension.version.isPresent -> "${libs.android.desugar.get().module}:${extension.version.get()}"
                else -> libs.android.desugar.get()
            }

            add("coreLibraryDesugaring", dependency)
        }

        with(androidExtension) {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}