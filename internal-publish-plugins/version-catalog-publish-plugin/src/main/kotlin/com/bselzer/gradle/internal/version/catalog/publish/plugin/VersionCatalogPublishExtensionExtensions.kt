package com.bselzer.gradle.internal.version.catalog.publish.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

private const val EXTENSION_NAME = "versionCatalogPublishExtension"

val Project.versionCatalogPublishExtension: VersionCatalogPublishExtension
    get() = extensions.findByName(EXTENSION_NAME) as? VersionCatalogPublishExtension ?: extensions.create(EXTENSION_NAME)

fun Project.versionCatalogPublishExtension(configure: VersionCatalogPublishExtension.() -> Unit) = versionCatalogPublishExtension.apply(configure)