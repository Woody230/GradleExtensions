package com.bselzer.gradle.internal.version.catalog

import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware

val ExtensionAware.namedVersionCatalogs: NamedVersionCatalogs
    get() {
        val extension = extensions.getByType(VersionCatalogsExtension::class.java)
        return NamedVersionCatalogs(extension)
    }

val ExtensionAware.libs: NamedVersionCatalog
    get() = namedVersionCatalogs.libs