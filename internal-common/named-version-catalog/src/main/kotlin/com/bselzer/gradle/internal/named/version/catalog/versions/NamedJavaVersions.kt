package com.bselzer.gradle.internal.named.version.catalog.versions

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionConstraint

class NamedJavaVersions internal constructor(private val versionCatalog: VersionCatalog) {
    val sourceCompatibility: VersionConstraint
        get() = versionCatalog.findVersion("java-sourceCompatibility").get()

    val targetCompatibility: VersionConstraint
        get() = versionCatalog.findVersion("java-targetCompatibility").get()

    val jdk: VersionConstraint
        get() = versionCatalog.findVersion("java-jdk").get()
}