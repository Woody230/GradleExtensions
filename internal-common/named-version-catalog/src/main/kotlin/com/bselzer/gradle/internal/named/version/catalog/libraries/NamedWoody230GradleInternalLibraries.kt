package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedWoody230GradleInternalLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    val libs: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("woody230-gradle-internal-libs").get()
}