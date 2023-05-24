package com.bselzer.gradle.internal.aboutlibraries.plugin

import com.bselzer.gradle.multiplatform.multiplatformDependencies
import com.mikepenz.aboutlibraries.plugin.AboutLibrariesExtension
import com.mikepenz.aboutlibraries.plugin.AboutLibrariesPlugin
import dev.icerock.gradle.MultiplatformResourcesPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AboutLibrariesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(AboutLibrariesPlugin::class.java)

        configureMultiplatform()
        configureMokoResources()
    }

    private fun Project.configureMultiplatform() {
        // TODO libs.plugins.multiplatform.get().pluginId
        if (!plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
            return
        }

        multiplatformDependencies {
            mainSourceSets {
                // TODO libs.aboutlibraries.core
                implementation("com.mikepenz:aboutlibraries-core:10.6.2")
            }
        }

        with(extensions.getByType<AboutLibrariesExtension>()) {
            registerAndroidTasks = false
        }
    }

    private fun Project.configureMokoResources() {
        // TODO libs.plugins.moko.resources.get().pluginId
        if (!plugins.hasPlugin("dev.icerock.mobile.multiplatform-resources")) {
            return
        }

        val sourceSetName = extensions.getByType<MultiplatformResourcesPluginExtension>().sourceSetName

        val aboutLibrariesResource = task("aboutLibrariesResource") {
            dependsOn("exportLibraryDefinitions")

            // Move aboutlibraries.json so that it can be used by moko-resources.
            copy {
                from("$buildDir\\generated\\aboutLibraries") {
                    include("aboutlibraries.json")
                }
                into("$projectDir\\src\\$sourceSetName\\resources\\MR\\assets")
            }
        }

        tasks.whenTaskAdded {
            if (name == "generateMR$sourceSetName") {
                dependsOn(aboutLibrariesResource)
            }
        }
    }
}