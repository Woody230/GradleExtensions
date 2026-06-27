package com.bselzer.gradle.internal.version.catalog.publish.plugin

import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishExtension
import org.gradle.api.file.ConfigurableFileCollection

interface VersionCatalogPublishExtension : MavenPublishExtension {
    /**
     * The contents of the version catalog to publish.
     */
    val from: ConfigurableFileCollection
}