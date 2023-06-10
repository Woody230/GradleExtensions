package com.bselzer.gradle.internal.bundled.plugin

import org.gradle.api.Plugin
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.Settings
import org.gradle.plugin.use.PluginDependenciesSpec

class BundledPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        pluginManagement {
            repositories.addRepositories()

            plugins {
                addToolchain()
            }
        }

        gradle.projectsLoaded {
            allprojects {
                repositories.addRepositories()
            }
        }
    }

    private fun RepositoryHandler.addRepositories() {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
    }

    private fun PluginDependenciesSpec.addToolchain() {
        // TODO libs
        id("org.gradle.toolchains.foojay-resolver-convention").version("0.4.0")
    }
}