package com.bselzer.gradle.internal.composite.build.plugin

import com.bselzer.gradle.internal.composite.task.plugin.CompositeTaskPlugin
import org.gradle.api.Project

class CompositeBuildPlugin : CompositeTaskPlugin() {
    private companion object {
        const val ASSEMBLE_INCLUDED_BUILDS = "assembleIncludedBuilds"
        const val ASSEMBLE_RECURSIVELY = "assemblyRecursively"
        const val ASSEMBLE = "assemble"

        const val BUILD_INCLUDED_BUILDS = "buildIncludedBuilds"
        const val BUILD_RECURSIVELY = "buildRecursively"
        const val BUILD = "build"

        const val CLEAN_INCLUDED_BUILDS = "cleanIncludedBuilds"
        const val CLEAN_RECURSIVELY = "cleanRecursively"
        const val CLEAN = "clean"
    }

    override fun Project.registerRootTasks() = listOf(
        tasks.register(ASSEMBLE_INCLUDED_BUILDS) {
            description = "Assembles the projects in the included builds."
            dependOnIncludedBuilds(":$ASSEMBLE_RECURSIVELY")
        },

        tasks.register(BUILD_INCLUDED_BUILDS) {
            description = "Assembles and tests the projects in the included builds."
            dependOnIncludedBuilds(":$BUILD_RECURSIVELY")
        },

        tasks.register(CLEAN_INCLUDED_BUILDS) {
            description = "Cleans the projects in the included builds."
            dependOnIncludedBuilds(":$CLEAN_RECURSIVELY")
        }
    )

    override fun Project.registerLeafTasks() = listOf(
        tasks.register(ASSEMBLE_RECURSIVELY) {
            description = "Assembles this project and all subprojects."
            dependOnRecursively(ASSEMBLE)
        },

        tasks.register(BUILD_RECURSIVELY) {
            description = "Assembles and tests this project and all subprojects."
            dependOnRecursively(BUILD)
        },

        tasks.register(CLEAN_RECURSIVELY) {
            description = "Cleans this project and all subprojects."
            dependOnRecursively(CLEAN)
        }
    )
}