package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.toolchains.foojay.FoojayToolchainsConventionPlugin

class ToolchainPlugin : Plugin<Settings> {
    override fun apply(settings: Settings): Unit = with(settings) {
        plugins.apply(FoojayToolchainsConventionPlugin::class.java)
    }
}