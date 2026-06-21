package com.bselzer.gradle.internal.named.version.catalog.versions

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionConstraint

class NamedWoody230Versions internal constructor(private val versionCatalog: VersionCatalog) {
    val gradle: VersionConstraint
        get() = versionCatalog.findVersion("woody230-gradle").get()
}