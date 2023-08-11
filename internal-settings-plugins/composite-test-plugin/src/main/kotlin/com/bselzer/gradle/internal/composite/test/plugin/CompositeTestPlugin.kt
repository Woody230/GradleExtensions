package com.bselzer.gradle.internal.composite.test.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings

class CompositeTestPlugin : Plugin<Settings> {
    private companion object {
        const val GROUP = "composite"
        const val TEST_BUILDS = "testBuilds"
        const val TEST_BUILD = "testBuild"
        const val ALL_TESTS = "allTests"
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
        tasks.register(TEST_BUILDS) {
            group = GROUP
            description = "Runs the tests for all targets within the included builds."

            val tasks = gradle.includedBuilds.map { build -> build.task(":${TEST_BUILD}") }
            dependsOn(tasks)
        }
    }

    private fun Project.addLeafTasks() {
        tasks.register(TEST_BUILD) {
            group = GROUP
            description = "Runs the tests for all targets within this build."

            val tasks = getTasksByName(ALL_TESTS, true)
            dependsOn(tasks)
        }
    }
}