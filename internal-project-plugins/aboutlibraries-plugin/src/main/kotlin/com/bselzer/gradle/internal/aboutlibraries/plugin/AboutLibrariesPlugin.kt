package com.bselzer.gradle.internal.aboutlibraries.plugin

import com.bselzer.gradle.internal.named.version.catalog.libs
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class AboutLibrariesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(libs.plugins.aboutlibraries.get().pluginId)

        configureMultiplatform()
        configureMokoResources()
    }

    private fun Project.configureMultiplatform() {
        if (!pluginManager.hasPlugin(libs.plugins.multiplatform.get().pluginId)) {
            return
        }

        multiplatformDependencies {
            mainSourceSets {
                implementation(libs.aboutlibraries.core)
            }
        }
    }

    private fun Project.configureMokoResources() {
        if (!pluginManager.hasPlugin(libs.plugins.moko.resources.get().pluginId)) {
            return
        }

        val sourceSetName = "commonMain"
        val aboutLibrariesResource = tasks.register("aboutLibrariesResource") {
            dependsOn("exportLibraryDefinitions")

            // Move aboutlibraries.json so that it can be used by moko-resources.
            copy {
                from("${layout.buildDirectory}\\generated\\aboutLibraries") {
                    include("aboutlibraries.json")
                }
                into("$projectDir\\src\\$sourceSetName\\moko-resources\\assets")
            }
        }

        tasks.whenTaskAdded {
            if (name == "generateMR$sourceSetName") {
                dependsOn(aboutLibrariesResource)
            }
        }
    }
}