package com.bselzer.gradle.internal.aboutlibraries.plugin

import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import com.mikepenz.aboutlibraries.plugin.AboutLibrariesExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AboutLibrariesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        // TODO libs.plugins.aboutlibraries.get().pluginId
        pluginManager.apply("com.mikepenz.aboutlibraries.plugin")

        configureMultiplatform()
        configureMokoResources()
    }

    private fun Project.configureMultiplatform() {
        // TODO libs.plugins.multiplatform.get().pluginId
        if (!pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
            return
        }

        multiplatformDependencies {
            mainSourceSets {
                // TODO libs.aboutlibraries.core
                implementation("com.mikepenz:aboutlibraries-core:11.2.1")
            }
        }

        with(extensions.getByType<AboutLibrariesExtension>()) {
            registerAndroidTasks = false
        }
    }

    private fun Project.configureMokoResources() {
        // TODO libs.plugins.moko.resources.get().pluginId
        if (!pluginManager.hasPlugin("dev.icerock.mobile.multiplatform-resources")) {
            return
        }

        val sourceSetName = "commonMain"
        val aboutLibrariesResource = task("aboutLibrariesResource") {
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