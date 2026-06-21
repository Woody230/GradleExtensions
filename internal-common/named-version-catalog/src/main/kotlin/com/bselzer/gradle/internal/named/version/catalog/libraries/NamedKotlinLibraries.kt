package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedKotlinLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    val reflect: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("kotlin-reflect").get()

    val test: NamedKotlinTestLibraries = NamedKotlinTestLibraries(versionCatalog)
}