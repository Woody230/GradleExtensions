package com.bselzer.gradle.internal.buildkonfig.plugin

import com.bselzer.gradle.internal.version.catalog.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.whenTaskAdded {
            if (name == "build") {
                dependsOn("generateBuildKonfig")
            }
        }

        pluginManager.apply(libs.plugins.buildkonfig.get().pluginId)
    }
}