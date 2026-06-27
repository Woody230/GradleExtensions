package com.bselzer.gradle.internal.version.catalog.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.logging.Logging

class VersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        val logger = Logging.getLogger(InternalVersionCatalogPlugin::class.java)

        // NOTE: need to be able to avoid recreating libs after dev.panuszewski.typesafe-conventions
        // https://github.com/radoslaw-panuszewski/typesafe-conventions-gradle-plugin/blob/main/src/main/kotlin/dev/panuszewski/gradle/conventioncatalogs/ConventionCatalogPlugin.kt
        gradle.settingsEvaluated {
            createVersionCatalog(logger, name = "libs", file = versionsFile)
        }
    }
}