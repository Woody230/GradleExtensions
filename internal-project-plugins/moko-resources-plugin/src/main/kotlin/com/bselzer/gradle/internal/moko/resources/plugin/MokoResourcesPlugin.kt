package com.bselzer.gradle.internal.moko.resources.plugin

import com.bselzer.gradle.android.androidExtensionOrNull
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformSourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

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