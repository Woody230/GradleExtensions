package com.bselzer.gradle.internal.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedAboutLibrariesLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    val core: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("com.mikepenz.aboutlibraries.plugin").get()
}