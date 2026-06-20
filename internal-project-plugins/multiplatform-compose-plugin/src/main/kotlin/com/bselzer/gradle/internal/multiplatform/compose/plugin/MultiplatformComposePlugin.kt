package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.internal.version.catalog.libs
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
            apply(libs.plugins.compose.asProvider().get().pluginId)
            apply(libs.plugins.compose.compiler.get().pluginId)
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