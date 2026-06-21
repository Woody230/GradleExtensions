package com.bselzer.gradle.internal.version.catalog.plugin

import com.bselzer.gradle.internal.named.version.catalog.libs
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class InternalVersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("ioGithubWoody230GradleInternalLibs") {
                    from(libs.woody230.gradle.internal.libs)
                }
            }
        }
    }
}