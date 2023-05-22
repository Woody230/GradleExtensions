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
        if (!plugins.hasPlugin(libs.plugins.multiplatform.get().pluginId)) {
            return
        }

        multiplatformDependencies {
            mainSourceSets {
                implementation(libs.aboutlibraries.core)
            }
        }

        with(extensions.getByType<AboutLibrariesExtension>()) {
            registerAndroidTasks = false
        }
    }

    private fun Project.configureMokoResources() {
        if (!plugins.hasPlugin(libs.plugins.moko.resources.get().pluginId)) {
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
            if (name == "generateMRcommonMain") {
                dependsOn(aboutLibrariesResource)
            }
        }
    }
}