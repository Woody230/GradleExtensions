package com.bselzer.gradle.android.application.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.bselzer.gradle.android.plugin.AndroidExtension
import com.bselzer.gradle.android.plugin.AndroidPlugin
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

        val extension = androidApplicationExtension {
            targetSdk.convention(33)

            // Prefer to have all languages available, otherwise in-app language changes won't have the strings available because they aren't downloaded.
            // https://developer.android.com/guide/app-bundle/configure-base#handling_language_changes
            // TODO on demand language downloading https://developer.android.com/guide/playcore/feature-delivery/on-demand#lang_resources
            languageSplit.convention(false)
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
        }
    }
}