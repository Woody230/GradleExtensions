package com.bselzer.gradle.internal.bundled.plugin

import com.bselzer.gradle.internal.composite.property.plugin.CompositePropertyPlugin
import com.bselzer.gradle.internal.composite.publish.plugin.CompositePublishPlugin
import com.bselzer.gradle.internal.version.catalog.plugin.VersionCatalogPlugin
import org.gradle.api.Plugin
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.Settings
import org.gradle.toolchains.foojay.FoojayToolchainsConventionPlugin

class BundledPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        buildscript.repositories.addRepositories()

        pluginManagement {
            repositories.addRepositories()
        }

        gradle.projectsLoaded {
            allprojects {
                repositories.addRepositories()
                buildscript.repositories.addRepositories()
            }
        }

        enableTypeSafeProjectAccessors()
        applyToolchainPlugin()
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
        plugins.apply(CompositePublishPlugin::class.java)
        plugins.apply(VersionCatalogPlugin::class.java)
    }

    private fun Settings.applyToolchainPlugin() {
        plugins.apply(FoojayToolchainsConventionPlugin::class.java)
    }

    private fun Settings.enableTypeSafeProjectAccessors() {
        // TODO feature preview https://docs.gradle.org/8.1.1/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

        // NOTE: need to ensure the root project name is set to resolve the following warning
        // Project accessors enabled, but root project name not explicitly set for '$NAME'. Checking out the project in different folders will impact the generated code and implicitly the buildscript classpath, breaking caching.
        rootProject.name = rootProject.projectDir.name
    }
}