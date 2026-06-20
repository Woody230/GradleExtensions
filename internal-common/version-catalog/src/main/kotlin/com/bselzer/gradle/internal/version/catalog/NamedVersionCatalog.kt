package com.bselzer.gradle.internal.version.catalog

import com.bselzer.gradle.internal.version.catalog.libraries.NamedAboutLibrariesLibraries
import com.bselzer.gradle.internal.version.catalog.libraries.NamedAndroidLibraries
import com.bselzer.gradle.internal.version.catalog.libraries.NamedAndroidxLibraries
import com.bselzer.gradle.internal.version.catalog.libraries.NamedKotlinLibraries
import com.bselzer.gradle.internal.version.catalog.plugins.NamedPlugins
import com.bselzer.gradle.internal.version.catalog.versions.NamedVersions
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

class NamedVersionCatalog internal constructor(private val versionCatalog: VersionCatalog) {
    val plugins: NamedPlugins = NamedPlugins(versionCatalog)
    val versions: NamedVersions = NamedVersions(versionCatalog)

    val aboutlibraries: NamedAboutLibrariesLibraries = NamedAboutLibrariesLibraries(versionCatalog)
    val android: NamedAndroidLibraries = NamedAndroidLibraries(versionCatalog)
    val androidx: NamedAndroidxLibraries = NamedAndroidxLibraries(versionCatalog)
    val kotlin: NamedKotlinLibraries = NamedKotlinLibraries(versionCatalog)
    val robolectric: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibrary("robolectric").get()
}