package com.bselzer.gradle.internal.buildkonfig.plugin

import com.codingfeline.buildkonfig.gradle.BuildKonfigPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(BuildKonfigPlugin::class.java)

        tasks.whenTaskAdded {
            if (name == "build") {
                dependsOn("generateBuildKonfig")
            }
        }
    }
}