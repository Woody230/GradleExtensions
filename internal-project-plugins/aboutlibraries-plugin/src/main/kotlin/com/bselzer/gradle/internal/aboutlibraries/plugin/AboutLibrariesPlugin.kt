package com.bselzer.gradle.internal.aboutlibraries.plugin

import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class AboutLibrariesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(BuildConfiguration.plugins_aboutlibraries)

        configureMultiplatform()
        configureMokoResources()
    }

    private fun Project.configureMultiplatform() =  pluginManager.withPlugin(BuildConfiguration.plugins_multiplatform) {
        multiplatformDependencies {
            mainSourceSets {
                implementation(BuildConfiguration.libs_aboutlibraries_core)
            }
        }
    }

    private fun Project.configureMokoResources() = pluginManager.withPlugin(BuildConfiguration.plugins_moko_resources) {
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
