package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = multiplatformComposeExtension

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtensionOrNull?.finalizeDslReceiver {
            configureCompose(extension)
        }

        with (pluginManager) {
            // TODO libs.plugins.compose.asProvider().get().pluginId
            apply("org.jetbrains.compose")

            // TODO libs.plugins.compose.compiler.get().pluginId
            apply("org.jetbrains.compose.compiler")
        }
    }

    private fun CommonExtension<*, *, *, *, *, *>.configureCompose(
        extension: MultiplatformComposeExtension
    ) {
        buildFeatures {
            compose = true
        }
        composeOptions {
            // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compatibility-and-versioning.html#kotlin-compatibility
            // https://github.com/JetBrains/compose-multiplatform/blob/master/gradle-plugins/compose/src/main/kotlin/org/jetbrains/compose/ComposeCompilerCompatibility.kt
            // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
            kotlinCompilerExtensionVersion = extension.compilerVersion.orNull
        }

        packaging {
            resources.pickFirsts.apply {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}