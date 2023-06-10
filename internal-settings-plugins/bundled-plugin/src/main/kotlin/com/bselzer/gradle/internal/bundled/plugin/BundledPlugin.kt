package com.bselzer.gradle.internal.bundled.plugin

import com.bselzer.gradle.internal.composite.property.plugin.CompositePropertyPlugin
import com.bselzer.gradle.internal.version.catalog.plugin.VersionCatalogPlugin
import org.gradle.api.Plugin
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.Settings
import org.gradle.plugin.use.PluginDependenciesSpec

class BundledPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        pluginManagement {
            repositories.addRepositories()

            plugins {
                applyToolchain()
            }
        }

        gradle.projectsLoaded {
            allprojects {
                repositories.addRepositories()
            }
        }

        // TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

        applyInternalPlugins()
    }

    private fun RepositoryHandler.addRepositories() {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
    }

    private fun PluginDependenciesSpec.applyToolchain() {
        // TODO libs
        id("org.gradle.toolchains.foojay-resolver-convention").version("0.4.0")
    }

    private fun Settings.applyInternalPlugins() {
        plugins.apply(CompositePropertyPlugin::class.java)
        plugins.apply(VersionCatalogPlugin::class.java)
    }
}