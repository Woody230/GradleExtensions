package com.bselzer.gradle.internal.android.application.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.bselzer.gradle.android.applicationAndroidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.android.release
import com.bselzer.gradle.function.properties.addOrReplaceProperty
import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.getProperty
import com.bselzer.gradle.function.properties.localProperties
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import org.gradle.api.Project

class AndroidApplicationPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidApplicationExtension
        get() = androidApplicationExtension

    override fun apply(project: Project) = with(project) {
        plugins.apply(AppPlugin::class.java)
        super.apply(project)

        setupGradleProperties()

        val extension = androidExtension.apply {
            targetSdk.convention(33)

            // Prefer to have all languages available, otherwise in-app language changes won't have the strings available because they aren't downloaded.
            // https://developer.android.com/guide/app-bundle/configure-base#handling_language_changes
            // TODO on demand language downloading https://developer.android.com/guide/playcore/feature-delivery/on-demand#lang_resources
            languageSplit.convention(false)

            defaultProguardFile.convention(DefaultProguardFile.OPTIMIZED)
            proguardFiles.convention(listOf("proguard-rules.pro"))
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

            bundle {
                language {
                    enableSplit = extension.languageSplit.get()
                }
            }

            proguard(extension.defaultProguardFile.get(), extension.proguardFiles.get())

            if (properties.containsKey(GradleProperty.STORE_FILE)) {
                signing(this)
            }
        }
    }

    private fun ApplicationExtension.proguard(
        defaultProguardFile: DefaultProguardFile,
        proguardFiles: Collection<String>
    ) = buildTypes.release {
        isMinifyEnabled = true
        isShrinkResources = true

        val files: Collection<Any> = listOf(getDefaultProguardFile(defaultProguardFile.fileName)) + proguardFiles
        proguardFiles(files)

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
        val localProperties = localProperties

        if (localProperties.containsKeys(LocalProperty.STORE_FILE)) {
            addOrReplaceProperty(GradleProperty.KEY_ALIAS, localProperties.getProperty(LocalProperty.KEY_ALIAS))
            addOrReplaceProperty(GradleProperty.KEY_PASSWORD, localProperties.getProperty(LocalProperty.KEY_PASSWORD))
            addOrReplaceProperty(GradleProperty.STORE_FILE, localProperties.getProperty(LocalProperty.STORE_FILE))
            addOrReplaceProperty(GradleProperty.STORE_PASSWORD, localProperties.getProperty(LocalProperty.STORE_PASSWORD))
        }
    }
}