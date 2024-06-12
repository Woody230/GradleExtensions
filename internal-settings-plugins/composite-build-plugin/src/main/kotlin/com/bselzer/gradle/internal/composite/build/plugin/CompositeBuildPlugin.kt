package com.bselzer.gradle.internal.composite.build.plugin

import com.bselzer.gradle.internal.composite.task.plugin.CompositeTaskPlugin
import org.gradle.api.Project

class CompositeBuildPlugin : CompositeTaskPlugin() {
    private companion object {
        const val BUILD_INCLUDED_BUILDS = "buildIncludedBuilds"
        const val BUILD_RECURSIVELY = "buildRecursively"
        const val BUILD = "build"
        const val CLEAN_INCLUDED_BUILDS = "cleanBuilds"
        const val CLEAN_RECURSIVELY = "cleanRecursively"
        const val CLEAN = "clean"
    }

    override fun Project.registerRootTasks() = listOf(
        tasks.register(BUILD_INCLUDED_BUILDS) {
            description = "Builds the included builds."
            dependOnIncludedBuilds(":$BUILD_RECURSIVELY")
        },

        tasks.register(CLEAN_INCLUDED_BUILDS) {
            description = "Cleans the included builds."
            dependOnIncludedBuilds(":$CLEAN_RECURSIVELY")
        }
    )

    override fun Project.registerLeafTasks() = listOf(
        tasks.register(BUILD_RECURSIVELY) {
            description = "Builds this project and all subprojects."
            dependOnRecursively(BUILD)
        },

        tasks.register(CLEAN_RECURSIVELY) {
            description = "Cleans this project and all subprojects."
            dependOnRecursively(CLEAN)
        }
    )
}