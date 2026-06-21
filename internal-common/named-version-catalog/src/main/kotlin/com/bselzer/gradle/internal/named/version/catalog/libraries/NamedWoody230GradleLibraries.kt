package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.VersionCatalog

class NamedWoody230GradleLibraries internal constructor(versionCatalog: VersionCatalog) {
    val internal: NamedWoody230GradleInternalLibraries = NamedWoody230GradleInternalLibraries(versionCatalog)
}