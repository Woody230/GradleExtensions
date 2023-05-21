package com.bselzer.gradle.android.application.plugin

import com.bselzer.gradle.android.plugin.AndroidExtension
import org.gradle.api.provider.ListProperty
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

    /**
     * The relative path from the root directory to the additional proguard files.
     */
    val proguardFiles: ListProperty<String>
}