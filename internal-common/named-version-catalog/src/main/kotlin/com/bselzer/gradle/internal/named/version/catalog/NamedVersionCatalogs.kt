package com.bselzer.gradle.internal.named.version.catalog

import org.gradle.api.artifacts.VersionCatalogsExtension

class NamedVersionCatalogs internal constructor(private val extension: VersionCatalogsExtension) {
    val libs: NamedVersionCatalog
        get() {
            val versionCatalog = extension.named("ioGithubWoody230GradleInternalLibs")
            return NamedVersionCatalog(versionCatalog)
        }
}