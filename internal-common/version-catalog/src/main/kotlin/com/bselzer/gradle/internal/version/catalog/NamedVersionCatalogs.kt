package com.bselzer.gradle.internal.version.catalog

import org.gradle.api.artifacts.VersionCatalogsExtension

class NamedVersionCatalogs internal constructor(private val extension: VersionCatalogsExtension) {
    val libs: NamedVersionCatalog
        get() {
            val versionCatalog = extension.named("libs")
            return NamedVersionCatalog(versionCatalog)
        }
}