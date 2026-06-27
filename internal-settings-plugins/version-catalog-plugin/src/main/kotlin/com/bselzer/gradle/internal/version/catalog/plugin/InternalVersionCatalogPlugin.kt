package com.bselzer.gradle.internal.version.catalog.plugin

import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class InternalVersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        dependencyResolutionManagement {
            repositories {
                mavenCentral()
                mavenLocal()
            }

            versionCatalogs {
                create("ioGithubWoody230GradleInternalLibs") {
                    from(BuildConfiguration.libs_woody230_gradle_internal_libs)
                }
            }
        }
    }
}