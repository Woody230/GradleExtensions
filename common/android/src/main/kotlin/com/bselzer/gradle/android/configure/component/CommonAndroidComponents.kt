package com.bselzer.gradle.android.configure.component

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.bselzer.gradle.android.androidComponentsExtension
import com.bselzer.gradle.android.androidComponentsExtensionOrNull
import org.gradle.api.Project

class CommonAndroidComponents internal constructor(
    private val project: Project
) : AndroidComponentsConfigurer<AndroidComponentsExtension<CommonExtension<*, *, *, *>, *, *>, CommonExtension<*, *, *, *>> by InternalAndroidComponentsConfigurer(
    project,
    { androidComponentsExtension as AndroidComponentsExtension<CommonExtension<*, *, *, *>, *, *> },
    {
        val components = androidComponentsExtensionOrNull ?: return@InternalAndroidComponentsConfigurer null
        return@InternalAndroidComponentsConfigurer components as AndroidComponentsExtension<CommonExtension<*, *, *, *>, *, *>
    }
)

val Project.commonAndroidComponents: CommonAndroidComponents
    get() = CommonAndroidComponents(this)

fun Project.commonAndroidComponents(configure: CommonAndroidComponents.() -> Unit) = commonAndroidComponents.configure()