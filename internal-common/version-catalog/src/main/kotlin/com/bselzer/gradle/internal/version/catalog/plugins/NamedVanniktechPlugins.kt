package com.bselzer.gradle.internal.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedVanniktechPlugins internal constructor(private val versionCatalog: VersionCatalog) {
    val publish: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("vanniktech-publish").get()
}