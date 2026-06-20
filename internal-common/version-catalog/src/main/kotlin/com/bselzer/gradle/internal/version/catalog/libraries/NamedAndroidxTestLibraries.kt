package com.bselzer.gradle.internal.version.catalog.libraries

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedAndroidxTestLibraries internal constructor(private val versionCatalog: VersionCatalog) {
    val compose: NamedAndroidxTestComposeLibraries = NamedAndroidxTestComposeLibraries(versionCatalog)

    val core: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("androidx-test-core").get()

    val junit: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("androidx-test-junit").get()

    val runner: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("androidx-test-runner").get()
}