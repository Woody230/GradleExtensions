package com.bselzer.gradle.internal.android.desugar.plugin

import com.bselzer.gradle.android.commonDslAndroidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDesugarPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val extension = androidDesugarExtension

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        commonDslAndroidComponentsExtension.finalizeDslReceiver {
            compileOptions.isCoreLibraryDesugaringEnabled = true

            dependencies {
                val dependency: Any = when {
                    extension.version.isPresent -> "${BuildConfiguration.libs_android_desugar_module}:${extension.version.get()}"
                    else -> BuildConfiguration.libs_android_desugar
                }

                add("coreLibraryDesugaring", dependency)
            }
        }
    }
}
