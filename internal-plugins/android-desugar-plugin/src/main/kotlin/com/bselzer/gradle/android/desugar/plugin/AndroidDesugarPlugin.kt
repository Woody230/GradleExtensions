package com.bselzer.gradle.android.desugar.plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

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

        with(extensions.getByType<LibraryExtension>()) {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}