package com.bselzer.gradle.internal.version.catalog.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class VersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        createVersionCatalog(name = "libs", versionsFile)
    }
}