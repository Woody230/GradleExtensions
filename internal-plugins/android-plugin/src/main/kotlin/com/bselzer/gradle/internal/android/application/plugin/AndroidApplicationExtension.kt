package com.bselzer.gradle.internal.android.application.plugin

import com.bselzer.gradle.internal.android.plugin.AndroidExtension
import org.gradle.api.provider.Property

interface AndroidApplicationExtension : AndroidExtension {
    /**
     * The id of the application.
     */
    val applicationId: Property<String>

    /**
     * The semantic version.
     */
    val versionName: Property<String>

    /**
     * The incremental version.
     */
    val versionCode: Property<Int>

    /**
     * The target API level.
     */
    val targetSdk: Property<Int>

    /**
     * Whether on-demand language downloading is enabled.
     */
    val languageSplit: Property<Boolean>

    /**
     * The type of default proguard file.
     */
    val defaultProguardFile: Property<DefaultProguardFile>
}