package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SettingsRepositoryPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        pluginManagement {
            repositories {
                google()
                gradlePluginPortal()
                mavenCentral()
            }
        }
    }
}