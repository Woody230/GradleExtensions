package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = multiplatformComposeExtension {
            // TODO libs.versions.androidx.compose.get()
            compilerVersion.convention("1.4.8")
        }

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtensionOrNull?.finalizeDslReceiver {
            configureCompose(extension)
        }

        // TODO libs.plugins.compose.get().pluginId
        plugins.apply("org.jetbrains.compose")
    }

    private fun CommonExtension<*, *, *, *>.configureCompose(
        extension: MultiplatformComposeExtension
    ) {
        buildFeatures {
            compose = true
        }
        composeOptions {
            // https://github.com/JetBrains/compose-multiplatform/blob/master/VERSIONING.md#kotlin-compatibility
            // https://github.com/JetBrains/compose-multiplatform/blob/master/gradle-plugins/compose/src/main/kotlin/org/jetbrains/compose/ComposeCompilerCompatibility.kt
            // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
            kotlinCompilerExtensionVersion = extension.compilerVersion.get()
        }

        packaging {
            resources.pickFirsts.apply {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}