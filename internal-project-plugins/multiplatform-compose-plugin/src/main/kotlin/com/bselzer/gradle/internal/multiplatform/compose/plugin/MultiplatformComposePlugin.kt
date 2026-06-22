package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import com.android.build.api.dsl.Packaging
import com.bselzer.gradle.android.applicationAndroidComponentsExtensionOrNull
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.libraryAndroidComponentsExtensionOrNull
import com.bselzer.gradle.android.multiplatformLibraryAndroidComponentsExtensionOrNull
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        applicationAndroidComponentsExtensionOrNull?.finalizeDslReceiver { configureCompose() }
        libraryAndroidComponentsExtensionOrNull?.finalizeDslReceiver { configureCompose() }
        multiplatformLibraryAndroidComponentsExtensionOrNull?.finalizeDslReceiver { configureCompose() }

        with (pluginManager) {
            apply(BuildConfiguration.plugins_compose)
            apply(BuildConfiguration.plugins_compose_compiler)
        }
    }

    private fun CommonExtension.configureCompose() {
        buildFeatures.compose = true

        packaging.configureCompose()
    }

    private fun KotlinMultiplatformAndroidLibraryExtension.configureCompose() {
        packaging.configureCompose()
    }

    private fun Packaging.configureCompose() {
        resources.pickFirsts.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}
