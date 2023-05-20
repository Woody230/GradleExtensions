package com.bselzer.gradle.android.application.plugin

import com.bselzer.gradle.android.plugin.AndroidExtension
import org.gradle.api.provider.Property

interface AndroidApplicationExtension : AndroidExtension {
    /**
     * The target API level.
     */
    val targetSdk: Property<Int>
}