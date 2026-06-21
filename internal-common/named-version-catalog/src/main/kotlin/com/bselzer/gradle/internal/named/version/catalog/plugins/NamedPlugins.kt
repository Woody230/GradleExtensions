package com.bselzer.gradle.internal.named.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedPlugins internal constructor(private val versionCatalog: VersionCatalog) {
    val aboutlibraries: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("aboutlibraries").get()

    val android: NamedAndroidPlugins = NamedAndroidPlugins(versionCatalog)

    val buildkonfig: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("buildkonfig").get()

    val compose: NamedComposePlugins = NamedComposePlugins(versionCatalog)

    val dokka: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("dokka").get()

    val gradle: NamedGradlePlugins = NamedGradlePlugins(versionCatalog)

    val java: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("java").get()

    val moko: NamedMokoPlugins = NamedMokoPlugins(versionCatalog)

    val multiplatform: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("multiplatform").get()

    val vanniktech: NamedVanniktechPlugins = NamedVanniktechPlugins(versionCatalog)

    val version: NamedVersionPlugins = NamedVersionPlugins(versionCatalog)
}