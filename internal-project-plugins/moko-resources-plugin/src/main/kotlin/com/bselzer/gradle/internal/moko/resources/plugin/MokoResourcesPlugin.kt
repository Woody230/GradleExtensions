package com.bselzer.gradle.internal.moko.resources.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MokoResourcesPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.whenTaskAdded {
            if (name == "generateMRandroidMain" || name == "generateMRjvmMain") {
                tasks.withType(org.gradle.jvm.tasks.Jar::class.java).forEach { task -> task.dependsOn(this) }
                tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class.java).forEach { task -> task.dependsOn(this) }
            }
        }

        // TODO libs.moko.resources.get().pluginId
        plugins.apply("dev.icerock.mobile.multiplatform-resources")
    }
}