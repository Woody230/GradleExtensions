package com.bselzer.gradle.internal.version.catalog.libraries

import org.gradle.api.artifacts.VersionCatalog

class NamedAndroidxLibraries internal constructor(versionCatalog: VersionCatalog) {
    val test: NamedAndroidxTestLibraries = NamedAndroidxTestLibraries(versionCatalog)
}