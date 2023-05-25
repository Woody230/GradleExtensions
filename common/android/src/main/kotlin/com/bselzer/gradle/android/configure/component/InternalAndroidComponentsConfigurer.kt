package com.bselzer.gradle.android.configure.component

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project

internal class InternalAndroidComponentsConfigurer<Components, CommonExtension>(
    private val project: Project,
    private val components: Project.() -> Components,
    private val componentsOrNull: Project.() -> Components?
) : AndroidComponentsConfigurer<Components, CommonExtension> where Components : AndroidComponentsExtension<CommonExtension, *, *>, CommonExtension : com.android.build.api.dsl.CommonExtension<*, *, *, *> {
    override fun finalizeDsl(configure: CommonExtension.() -> Unit) = components(project).finalizeDsl(configure)

    override fun maybeFinalizeDsl(configure: CommonExtension.() -> Unit) {
        componentsOrNull(project)?.finalizeDsl(configure)
    }
}