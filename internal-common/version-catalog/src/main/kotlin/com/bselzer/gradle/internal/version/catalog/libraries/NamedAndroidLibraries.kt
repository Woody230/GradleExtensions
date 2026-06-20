package com.bselzer.gradle.internal.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedAndroidLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    val desugar: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("android-desugar").get()
}