package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.android.commonDslAndroidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        commonDslAndroidComponentsExtensionOrNull?.finalizeDslReceiver {
            configureCompose()
        }

        with (pluginManager) {
            apply(BuildConfiguration.plugins_compose)
            apply(BuildConfiguration.plugins_compose_compiler)
        }
    }

    private fun CommonExtension.configureCompose() {
        buildFeatures.compose = true

        packaging.resources.pickFirsts.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}
