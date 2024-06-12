package com.bselzer.gradle.internal.composite.build.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class CompositeBuildPlugin : Plugin<Settings> {
    private companion object {
        const val GROUP = "composite"
        const val BUILD_INCLUDED_BUILDS = "buildIncludedBuilds"
        const val BUILD_RECURSIVE = "buildRecursive"
        const val BUILD = "build"
        const val CLEAN_INCLUDED_BUILDS = "cleanBuilds"
        const val CLEAN_RECURSIVE = "cleanRecursive"
        const val CLEAN = "clean"
    }

    override fun apply(settings: Settings) = with(settings) {
        gradle.projectsLoaded {
            if (parent == null) {
                rootProject.addRootTasks()
            } else {
                rootProject.addLeafTasks()
            }
        }
    }

    private fun Project.addRootTasks() {
        // NOTE assumption is that the root project does not actually have any projects itself and purely relies on included builds.
        tasks.register(BUILD_INCLUDED_BUILDS) {
            group = GROUP
            description = "Builds the included builds."

            val tasks = gradle.includedBuilds.map { build -> build.task(":$BUILD_RECURSIVE") }
            dependsOn(tasks)
        }

        tasks.register(CLEAN_INCLUDED_BUILDS) {
            group = GROUP
            description = "Cleans the included builds."

            val tasks = gradle.includedBuilds.map { build -> build.task(":$CLEAN_RECURSIVE") }
            dependsOn(tasks)
        }
    }

    private fun Project.addLeafTasks() {
        tasks.register(BUILD_RECURSIVE) {
            group = GROUP
            description = "Builds this project and all subprojects."

            val tasks = getTasksByName(BUILD, true)
            dependsOn(tasks)
        }

        tasks.register(CLEAN_RECURSIVE) {
            group = GROUP
            description = "Cleans this project and all subprojects."

            val tasks = gradle.includedBuilds.map { build -> build.task(":$CLEAN") }
            dependsOn(tasks)
        }
    }
}