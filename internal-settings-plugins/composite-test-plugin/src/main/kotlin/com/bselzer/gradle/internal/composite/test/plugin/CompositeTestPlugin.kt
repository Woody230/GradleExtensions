package com.bselzer.gradle.internal.composite.test.plugin

import com.bselzer.gradle.internal.composite.task.plugin.CompositeTaskPlugin
import org.gradle.api.Project

class CompositeTestPlugin : CompositeTaskPlugin() {
    private companion object {
        const val TEST_INCLUDED_BUILDS = "testIncludedBuilds"
        const val TEST_RECURSIVELY = "testRecursively"
        const val ALL_TESTS = "allTests"
    }

    override fun Project.registerRootTasks() = listOf(
        tasks.register(TEST_INCLUDED_BUILDS) {
            description = "Runs the tests for all targets within the included builds."
            dependOnIncludedBuilds(":$TEST_RECURSIVELY")
        }
    )

    override fun Project.registerLeafTasks() = listOf(
        tasks.register(TEST_RECURSIVELY) {
            description = "Runs the tests for all targets within this build."
            dependOnRecursively(ALL_TESTS)
        }
    )
}