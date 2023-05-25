package com.bselzer.gradle.android.configure.component

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.bselzer.gradle.android.applicationAndroidComponentsExtension
import com.bselzer.gradle.android.applicationAndroidComponentsExtensionOrNull
import org.gradle.api.Project

class ApplicationAndroidComponents internal constructor(
    project: Project
) : AndroidComponentsConfigurer<ApplicationAndroidComponentsExtension, ApplicationExtension> by InternalAndroidComponentsConfigurer(
    project,
    { applicationAndroidComponentsExtension },
    { applicationAndroidComponentsExtensionOrNull }
)

val Project.applicationAndroidComponents: ApplicationAndroidComponents
    get() = ApplicationAndroidComponents(this)

fun Project.applicationAndroidComponents(configure: ApplicationAndroidComponents.() -> Unit) = applicationAndroidComponents.configure()