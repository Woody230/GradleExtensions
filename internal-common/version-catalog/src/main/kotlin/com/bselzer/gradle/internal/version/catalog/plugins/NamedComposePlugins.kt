package com.bselzer.gradle.internal.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedComposePlugins internal constructor(private val versionCatalog: VersionCatalog) {
    fun asProvider(): Provider<PluginDependency> = versionCatalog.findPlugin("compose").get()

    val compiler: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("compose-compiler").get()
}