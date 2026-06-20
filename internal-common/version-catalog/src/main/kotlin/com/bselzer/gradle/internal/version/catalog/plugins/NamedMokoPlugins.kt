package com.bselzer.gradle.internal.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedMokoPlugins internal constructor(private val versionCatalog: VersionCatalog) {
    val resources: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("moko-resources").get()
}