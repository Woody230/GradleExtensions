package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

class NamedAndroidxTestComposeUiLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    fun asProvider(): Provider<PluginDependency> = versionCatalog.findPlugin("androidx-test-compose-ui").get()

    val junit: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("androidx-test-compose-ui-junit").get()

    val manifest: Provider<PluginDependency>
        get() = versionCatalog.findPlugin("androidx-text-compose-ui-manifest").get()
}