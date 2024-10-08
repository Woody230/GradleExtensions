package com.bselzer.gradle.internal.android.desugar.plugin

import com.bselzer.gradle.android.androidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDesugarPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val extension = androidDesugarExtension

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtension.finalizeDslReceiver {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }

            dependencies {
                val dependency: Any = when {
                    // TODO libs.android.desugar.get().module
                    extension.version.isPresent -> "com.android.tools:desugar_jdk_libs:${extension.version.get()}"

                    // TODO libs.android.desugar.get()
                    else -> "com.android.tools:desugar_jdk_libs:2.0.4"
                }

                add("coreLibraryDesugaring", dependency)
            }
        }
    }
}