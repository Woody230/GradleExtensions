package com.bselzer.gradle.internal.version.catalog.versions

import org.gradle.api.artifacts.VersionCatalog

class NamedVersions internal constructor(versionCatalog: VersionCatalog) {
    val android: NamedAndroidVersions = NamedAndroidVersions(versionCatalog)
    val java: NamedJavaVersions = NamedJavaVersions(versionCatalog)
}