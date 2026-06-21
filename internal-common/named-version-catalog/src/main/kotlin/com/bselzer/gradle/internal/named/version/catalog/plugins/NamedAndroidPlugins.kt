package com.bselzer.gradle.internal.named.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedAndroidPlugins internal constructor(private val versionCatalog: VersionCatalog) {
    val application: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("android-application").get()

    val library: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("android-library").get()
}