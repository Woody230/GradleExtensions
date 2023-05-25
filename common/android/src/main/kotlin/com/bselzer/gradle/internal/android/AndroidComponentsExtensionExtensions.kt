package com.bselzer.gradle.internal.android

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Project

val Project.androidComponentsExtension: AndroidComponentsExtension<*, *, *>
    get() = extensions.getByType(AndroidComponentsExtension::class.java)

val Project.androidComponentsExtensionOrNull: AndroidComponentsExtension<*, *, *>?
    get() = extensions.findByType(AndroidComponentsExtension::class.java)

val Project.applicationAndroidComponentsExtension: ApplicationAndroidComponentsExtension
    get() = extensions.getByType(ApplicationAndroidComponentsExtension::class.java)

val Project.applicationAndroidComponentsExtensionOrNull: ApplicationAndroidComponentsExtension?
    get() = extensions.findByType(ApplicationAndroidComponentsExtension::class.java)


