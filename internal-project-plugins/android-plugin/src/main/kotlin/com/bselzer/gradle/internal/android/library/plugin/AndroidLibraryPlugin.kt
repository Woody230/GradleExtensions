package com.bselzer.gradle.internal.android.library.plugin

import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.libraryAndroidComponentsExtension
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Project

class AndroidLibraryPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidLibraryExtension
        get() = androidLibraryExtension

    override fun apply(project: Project) = with(project) {
        pluginManager.apply(BuildConfiguration.plugins_android_library)

        super.apply(project)

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        libraryAndroidComponentsExtension.finalizeDslReceiver {
            logger.info("Finalizing the Android library.")
            finalizeConfigureAndroid(androidExtension)
        }
    }
}
