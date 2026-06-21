package com.bselzer.gradle.internal.version.catalog.plugin

import com.bselzer.gradle.internal.named.version.catalog.libs
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class InternalVersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("ioGithubWoody230GradleInternal") {
                    from("io.github.woody230.gradle:catalog:${libs.versions.woody230.gradle.requiredVersion}")
                }
            }
        }
    }
}