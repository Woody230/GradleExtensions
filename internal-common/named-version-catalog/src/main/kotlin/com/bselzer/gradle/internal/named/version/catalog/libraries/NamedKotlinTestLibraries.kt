package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedKotlinTestLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    fun asProvider(): Provider<MinimalExternalModuleDependency> = versionCatalog.findLibrary("kotlin-test").get()

    val annotations: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("kotlin-test-annotations").get()

    val junit: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("kotlin-test-junit").get()
}