package com.bselzer.gradle.internal.buildkonfig.plugin

import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.whenTaskAdded {
            if (name == "build") {
                dependsOn("generateBuildKonfig")
            }
        }

        pluginManager.apply(BuildConfiguration.plugins_buildkonfig)
    }
}
