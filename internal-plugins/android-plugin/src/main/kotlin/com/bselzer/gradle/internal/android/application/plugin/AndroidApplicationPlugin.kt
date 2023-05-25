package com.bselzer.gradle.internal.android.application.plugin

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.bselzer.gradle.function.properties.addOrReplaceProperty
import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.getProperty
import com.bselzer.gradle.function.properties.localProperties
import com.bselzer.gradle.internal.android.plugin.AndroidPlugin
import com.bselzer.gradle.internal.android.release
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : AndroidPlugin() {
    override val Project.androidExtension: AndroidApplicationExtension
        get() = androidApplicationExtension

    override val Project.commonExtension: BaseAppModuleExtension
        get() = extensions.getByType<BaseAppModuleExtension>()

    override fun apply(project: Project) = with(project) {
        plugins.apply(AppPlugin::class.java)
        super.apply(project)

        setupGradleProperties()

        val extension = androidExtension.apply {
            // NOTE: base logic has been applied, so we can just get the namespace that was set
            applicationId.convention("${extensions.getByType<BaseAppModuleExtension>().namespace}.android")
            targetSdk.convention(33)

            // Prefer to have all languages available, otherwise in-app language changes won't have the strings available because they aren't downloaded.
            // https://developer.android.com/guide/app-bundle/configure-base#handling_language_changes
            // TODO on demand language downloading https://developer.android.com/guide/playcore/feature-delivery/on-demand#lang_resources
            languageSplit.convention(false)

            defaultProguardFile.convention(DefaultProguardFile.OPTIMIZED)
            proguardFiles.convention(listOf("proguard-rules.pro"))
        }

        afterEvaluate {
            with(commonExtension) {
                defaultConfig {
                    applicationId = extension.applicationId.get()
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
            addOrReplaceProperty(GradleProperty.KEY_ALIAS, localProperties.getProperty(LocalProperty.KEY_ALIAS))
            addOrReplaceProperty(GradleProperty.KEY_PASSWORD, localProperties.getProperty(LocalProperty.KEY_PASSWORD))
            addOrReplaceProperty(GradleProperty.STORE_FILE, localProperties.getProperty(LocalProperty.STORE_FILE))
            addOrReplaceProperty(GradleProperty.STORE_PASSWORD, localProperties.getProperty(LocalProperty.STORE_PASSWORD))
        }
    }
}