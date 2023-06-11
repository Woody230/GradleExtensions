package com.bselzer.gradle.internal.bundled.plugin

import com.bselzer.gradle.internal.composite.property.plugin.CompositePropertyPlugin
import com.bselzer.gradle.internal.version.catalog.plugin.VersionCatalogPlugin
import org.gradle.api.Plugin
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.Settings
import org.gradle.toolchains.foojay.FoojayToolchainsConventionPlugin

class BundledPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        pluginManagement {
            repositories.addRepositories()
        }

        gradle.projectsLoaded {
            allprojects {
                repositories.addRepositories()
            }
        }

        // TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

        plugins.apply(FoojayToolchainsConventionPlugin::class.java)
        applyInternalPlugins()
    }

    private fun RepositoryHandler.addRepositories() {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
    }

    private fun Settings.applyInternalPlugins() {
        plugins.apply(CompositePropertyPlugin::class.java)
        plugins.apply(VersionCatalogPlugin::class.java)
    }
}