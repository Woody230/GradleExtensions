package com.bselzer.gradle.internal.moko.resources.plugin

import com.bselzer.gradle.android.androidExtensionOrNull
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class MokoResourcesPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        // TODO libs.moko.resources.get().pluginId
        plugins.apply("dev.icerock.mobile.multiplatform-resources")

        // TODO temporary srcDir inclusion https://github.com/icerockdev/moko-resources/issues/353
        androidExtensionOrNull?.sourceSets?.getByName("main")?.let { sourceSet ->
            //sourceSet.assets.srcDir(File(buildDir, "generated/moko/androidMain/assets"))
            sourceSet.res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
        }

        tasks.whenTaskAdded {
            if (name == "generateMRandroidMain") {
                tasks.withType(org.gradle.jvm.tasks.Jar::class.java).forEach { task -> task.dependsOn(this) }
            }
        }

        tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class.java).forEach { task ->
            task.dependsOn("generateMRandroidMain")
        }
    }
}