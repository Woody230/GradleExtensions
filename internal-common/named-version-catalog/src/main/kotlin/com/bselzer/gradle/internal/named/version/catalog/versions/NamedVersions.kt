package com.bselzer.gradle.internal.named.version.catalog.versions

import org.gradle.api.artifacts.VersionCatalog

class NamedVersions internal constructor(versionCatalog: VersionCatalog) {
    val android: NamedAndroidVersions = NamedAndroidVersions(versionCatalog)
    val java: NamedJavaVersions = NamedJavaVersions(versionCatalog)
    val woody230: NamedWoody230Versions = NamedWoody230Versions(versionCatalog)
}