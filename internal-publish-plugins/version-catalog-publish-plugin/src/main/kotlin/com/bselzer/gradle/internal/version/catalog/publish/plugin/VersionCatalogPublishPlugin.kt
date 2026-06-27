package com.bselzer.gradle.internal.version.catalog.publish.plugin

import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishExtension
import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishPlugin
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project
import org.gradle.api.plugins.catalog.CatalogPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import com.vanniktech.maven.publish.VersionCatalog as VersionCatalogPlatform

class VersionCatalogPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: MavenPublishExtension
        get() = versionCatalogPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() = VersionCatalogPlatform()

    override fun apply(project: Project) = with(project) {
        val extension = versionCatalogPublishExtension

        afterEvaluate {
            with (extensions.getByType<CatalogPluginExtension>()) {
                versionCatalog {
                    from(extension.from)
                }
            }
        }

        apply(plugin = BuildConfiguration.plugins_version_catalog_publish)
        super.apply(project)
    }
}
