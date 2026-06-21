package com.bselzer.gradle.internal.named.version.catalog.libraries

import org.gradle.api.artifacts.VersionCatalog

class NamedWoody230Libraries internal constructor(versionCatalog: VersionCatalog) {
    val gradle: NamedWoody230GradleLibraries = NamedWoody230GradleLibraries(versionCatalog)
}