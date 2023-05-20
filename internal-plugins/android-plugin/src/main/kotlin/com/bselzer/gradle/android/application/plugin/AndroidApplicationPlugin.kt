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
    }
}