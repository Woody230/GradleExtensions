package com.bselzer.gradle.internal.named.version.catalog.versions

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionConstraint

class NamedAndroidVersions internal constructor(private val versionCatalog: VersionCatalog) {
    val compileSdk: VersionConstraint
        get() = versionCatalog.findVersion("android-compileSdk").get()

    val minSdk: VersionConstraint
        get() = versionCatalog.findVersion("android-minSdk").get()

    val targetSdk: VersionConstraint
        get() = versionCatalog.findVersion("android-targetSdk").get()
}