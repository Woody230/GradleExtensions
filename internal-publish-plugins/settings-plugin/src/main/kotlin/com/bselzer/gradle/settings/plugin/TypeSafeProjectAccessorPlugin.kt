package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class TypeSafeProjectAccessorPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        // TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    }
}