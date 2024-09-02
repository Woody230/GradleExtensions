package com.bselzer.gradle.internal.buildkonfig.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.whenTaskAdded {
            if (name == "build") {
                dependsOn("generateBuildKonfig")
            }
        }

        // TODO libs.plugins.buildkonfig.get().pluginId
        pluginManager.apply("com.codingfeline.buildkonfig")
    }
}