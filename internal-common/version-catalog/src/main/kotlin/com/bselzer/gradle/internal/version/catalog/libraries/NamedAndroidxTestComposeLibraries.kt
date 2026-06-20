package com.bselzer.gradle.internal.version.catalog.libraries

import org.gradle.api.artifacts.VersionCatalog

class NamedAndroidxTestComposeLibraries internal constructor(versionCatalog: VersionCatalog) {
    val ui: NamedAndroidxTestComposeUiLibraries = NamedAndroidxTestComposeUiLibraries(versionCatalog)
}