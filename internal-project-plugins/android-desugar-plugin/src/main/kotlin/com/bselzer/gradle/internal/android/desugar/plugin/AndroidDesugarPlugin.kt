package com.bselzer.gradle.internal.android.desugar.plugin

import com.bselzer.gradle.android.applicationAndroidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.libraryAndroidComponentsExtensionOrNull
import com.bselzer.gradle.android.multiplatformLibraryAndroidComponentsExtensionOrNull
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDesugarPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = androidDesugarExtension

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        applicationAndroidComponentsExtensionOrNull?.finalizeDslReceiver {
            compileOptions.isCoreLibraryDesugaringEnabled = true
            addDesugaringDependency(extension)
        }

        libraryAndroidComponentsExtensionOrNull?.finalizeDslReceiver {
            compileOptions.isCoreLibraryDesugaringEnabled = true
            addDesugaringDependency(extension)
        }

        multiplatformLibraryAndroidComponentsExtensionOrNull?.finalizeDslReceiver {
            enableCoreLibraryDesugaring = true
            addDesugaringDependency(extension)
        }
    }

    fun Project.addDesugaringDependency(extension: AndroidDesugarExtension) {
        dependencies {
            val dependency: Any = when {
                extension.version.isPresent -> "${BuildConfiguration.libs_android_desugar_module}:${extension.version.get()}"
                else -> BuildConfiguration.libs_android_desugar
            }

            add("coreLibraryDesugaring", dependency)
        }
    }
}
