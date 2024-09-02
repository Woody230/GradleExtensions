package com.bselzer.gradle.internal.android.application.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.bselzer.gradle.android.applicationAndroidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.release
import com.bselzer.gradle.function.properties.addOrReplaceProperty
import com.bselzer.gradle.function.properties.compositeLocalProperties
import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.getProperty
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree

class AndroidApplicationPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidApplicationExtension
        get() = androidApplicationExtension

    override fun apply(project: Project) = with(project) {
        // TODO libs.plugins.android.application.get().pluginId
        pluginManager.apply("com.android.application")
        super.apply(project)

        setupGradleProperties()

        val extension = androidExtension.apply {
            // TODO libs.versions.android.targetSdk.get().toInt()
            targetSdk.convention(35)
            defaultProguardFile.convention(DefaultProguardFile.OPTIMIZED)
            buildConfig.convention(true)
        }

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        applicationAndroidComponentsExtension.finalizeDslReceiver {
            defaultConfig {
                applicationId = if (extension.applicationId.isPresent) extension.applicationId.get() else namespace
                targetSdk = extension.targetSdk.get()
                versionName = extension.versionName.get()
                versionCode = extension.versionCode.get()
            }

            proguard(extension.defaultProguardFile.get(), fileTree("proguard") {
                include("*.pro")
            })

            if (properties.containsKey(GradleProperty.STORE_FILE)) {
                signing(this)
            }
        }
    }

    private fun ApplicationExtension.proguard(
        defaultProguardFile: DefaultProguardFile,
        additionalProguardFiles: FileTree
    ) = buildTypes.release {
        isMinifyEnabled = true
        isShrinkResources = true

        proguardFile(getDefaultProguardFile(defaultProguardFile.fileName))
        additionalProguardFiles.files.forEach { file -> proguardFile(file) }

        ndk.debugSymbolLevel = "FULL"
    }

    private fun Project.signing(app: ApplicationExtension) = with(app) {
        buildTypes.release {
            signingConfig = signingConfigs.release {
                storeFile = file(getProperty(GradleProperty.STORE_FILE))
                storePassword = getProperty(GradleProperty.STORE_PASSWORD)
                keyPassword = getProperty(GradleProperty.KEY_PASSWORD)
                keyAlias = getProperty(GradleProperty.KEY_ALIAS)
            }
        }
    }

    private fun Project.setupGradleProperties() {
        val localProperties = compositeLocalProperties

        if (localProperties.containsKeys(LocalProperty.STORE_FILE)) {
            addOrReplaceProperty(GradleProperty.KEY_ALIAS, localProperties.getProperty(LocalProperty.KEY_ALIAS))
            addOrReplaceProperty(GradleProperty.KEY_PASSWORD, localProperties.getProperty(LocalProperty.KEY_PASSWORD))
            addOrReplaceProperty(GradleProperty.STORE_FILE, localProperties.getProperty(LocalProperty.STORE_FILE))
            addOrReplaceProperty(GradleProperty.STORE_PASSWORD, localProperties.getProperty(LocalProperty.STORE_PASSWORD))
        }
    }
}