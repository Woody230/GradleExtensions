package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = multiplatformComposeExtension {
            // TODO libs.versions.androidx.compose.compiler.get()
            compilerVersion.convention("1.5.10")
        }

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtensionOrNull?.finalizeDslReceiver {
            configureCompose(extension)
        }

        // TODO libs.plugins.compose.get().pluginId
        plugins.apply("org.jetbrains.compose")

        extensions.getByType<ComposeExtension>().apply {
            configureCompose(extension)
        }
    }

    private fun CommonExtension<*, *, *, *, *>.configureCompose(
        extension: MultiplatformComposeExtension
    ) {
        buildFeatures {
            compose = true
        }
        composeOptions {
            // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compatibility-and-versioning.html#kotlin-compatibility
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

    private fun ComposeExtension.configureCompose(extension: MultiplatformComposeExtension) {
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compatibility-and-versioning.html#jetpack-compose-and-compose-multiplatform-release-cycles
        // NOTE: using the Jetpack compiler is fine when relying only on Android/Desktop targets, otherwise the multiplatform version should be used
        // TODO libs.androidx.compose.compiler.get()
        kotlinCompilerPlugin.set("androidx.compose.compiler:compiler:${extension.compilerVersion.get()}")
    }
}