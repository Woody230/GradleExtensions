package com.bselzer.gradle.kotlin.multiplatform.resource.plugin

import com.android.build.gradle.LibraryExtension
import dev.icerock.gradle.MultiplatformResourcesPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class MultiplatformResourcesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(MultiplatformResourcesPlugin::class.java)

        // TODO temporary srcDir inclusion https://github.com/icerockdev/moko-resources/issues/353
        val android = extensions.getByType(LibraryExtension::class.java)
        with(android.sourceSets.getByName("main")) {
            assets.srcDir(File(buildDir, "generated/moko/androidMain/assets"))
            res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
        }

        // TODO temporarily explicitly declare dependency
        tasks.whenTaskAdded {
            if (name == "generateMRandroidMain") {
                tasks.withType(org.gradle.jvm.tasks.Jar::class.java).forEach { task -> task.dependsOn(this) }
            }
        }

        tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class.java).forEach { task ->
            task.dependsOn("generateMRandroidMain")
            task.dependsOn("generateMRjvmMain")
        }
    }
}