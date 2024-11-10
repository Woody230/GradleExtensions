package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtensionOrNull?.finalizeDslReceiver {
            configureCompose()
        }

        with (pluginManager) {
            // TODO libs.plugins.compose.asProvider().get().pluginId
            apply("org.jetbrains.compose")

            // TODO libs.plugins.compose.compiler.get().pluginId
            apply("org.jetbrains.kotlin.plugin.compose")
        }
    }

    private fun CommonExtension<*, *, *, *, *, *>.configureCompose() {
        buildFeatures {
            compose = true
        }
        packaging {
            resources.pickFirsts.apply {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}