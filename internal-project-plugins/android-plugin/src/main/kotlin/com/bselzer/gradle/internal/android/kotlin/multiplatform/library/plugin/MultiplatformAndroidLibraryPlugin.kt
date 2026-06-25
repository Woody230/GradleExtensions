package com.bselzer.gradle.internal.android.kotlin.multiplatform.library.plugin

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.multiplatformLibraryAndroidComponentsExtension
import com.bselzer.gradle.internal.android.plugin.AndroidExtension
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Project

class MultiplatformAndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: MultiplatformAndroidLibraryExtension
        get() = multiplatformAndroidLibraryExtension

    override fun apply(project: Project) = with(project) {
        pluginManager.apply(BuildConfiguration.plugins_android_kotlin_multiplatform_library)

        super.apply(project)

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        multiplatformLibraryAndroidComponentsExtension.finalizeDslReceiver {
            logger.info("Finalizing the Android multiplatform library.")
            finalizeConfigureAndroid(androidExtension)
        }
    }

    private fun KotlinMultiplatformAndroidLibraryExtension.finalizeConfigureAndroid(extension: AndroidExtension) {
        namespace = "${extension.namespace.group.get()}.${extension.namespace.category.get()}.${extension.namespace.module.get()}".replace("-", ".")
        compileSdk = extension.compileSdk.get()
        minSdk = extension.minSdk.get()

        withHostTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            isIncludeAndroidResources = true
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = extension.testInstrumentationRunner.get()
            execution = "HOST"
        }

        androidResources {
            enable = true
        }
    }
}
