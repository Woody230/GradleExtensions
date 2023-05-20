package com.bselzer.gradle.android.application.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.bselzer.gradle.android.plugin.AndroidExtension
import com.bselzer.gradle.android.plugin.AndroidPlugin
import com.bselzer.gradle.android.release
import com.bselzer.gradle.properties.containsKeys
import com.bselzer.gradle.properties.getProperty
import com.bselzer.gradle.properties.localProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidExtension
        get() = androidApplicationExtension

    override val Project.commonExtension: CommonExtension<*, *, *, *>
        get() = extensions.getByType<BaseAppModuleExtension>()

    override fun apply(project: Project) = with(project) {
        plugins.apply(AppPlugin::class.java)
        super.apply(project)

        setupGradleProperties()

        val extension = androidApplicationExtension {
            targetSdk.convention(33)

            // Prefer to have all languages available, otherwise in-app language changes won't have the strings available because they aren't downloaded.
            // https://developer.android.com/guide/app-bundle/configure-base#handling_language_changes
            // TODO on demand language downloading https://developer.android.com/guide/playcore/feature-delivery/on-demand#lang_resources
            languageSplit.convention(false)

            defaultProguardFile.convention(DefaultProguardFile.OPTIMIZED)
            proguardFiles.convention(listOf("proguard-rules.pro"))
        }

        with(extensions.getByType<BaseAppModuleExtension>()) {
            defaultConfig {
                targetSdk = extension.targetSdk.get()
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

    private fun BaseAppModuleExtension.proguard(
        defaultProguardFile: DefaultProguardFile,
        proguardFiles: Collection<String>
    ) = buildTypes.release {
        isMinifyEnabled = true
        isShrinkResources = true

        val files: Collection<Any> = listOf(getDefaultProguardFile(defaultProguardFile.fileName)) + proguardFiles
        proguardFiles(files)

        ndk.debugSymbolLevel = "FULL"
    }

    private fun Project.signing(app: BaseAppModuleExtension) = with(app) {
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
            setProperty(GradleProperty.KEY_ALIAS, localProperties.getProperty(LocalProperty.KEY_ALIAS))
            setProperty(GradleProperty.KEY_PASSWORD, localProperties.getProperty(LocalProperty.KEY_PASSWORD))
            setProperty(GradleProperty.STORE_FILE, localProperties.getProperty(LocalProperty.STORE_FILE))
            setProperty(GradleProperty.STORE_PASSWORD, localProperties.getProperty(LocalProperty.STORE_PASSWORD))
        }
    }
}