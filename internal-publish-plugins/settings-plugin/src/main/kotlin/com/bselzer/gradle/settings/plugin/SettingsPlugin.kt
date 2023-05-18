package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings): Unit = with(settings) {
        plugins.apply(SettingsRepositoryPlugin::class.java)
        plugins.apply(ToolchainPlugin::class.java)
        plugins.apply(TypeSafeProjectAccessorPlugin::class.java)
    }
}