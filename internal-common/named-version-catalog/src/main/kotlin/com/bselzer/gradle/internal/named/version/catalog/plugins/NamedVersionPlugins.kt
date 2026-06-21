package com.bselzer.gradle.internal.named.version.catalog.plugins

import org.gradle.api.artifacts.VersionCatalog

class NamedVersionPlugins internal constructor(versionCatalog: VersionCatalog) {
    val catalog: NamedVersionCatalogPlugins = NamedVersionCatalogPlugins(versionCatalog)
}