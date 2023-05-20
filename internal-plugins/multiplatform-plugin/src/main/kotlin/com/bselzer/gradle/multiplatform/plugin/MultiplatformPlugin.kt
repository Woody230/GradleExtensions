package com.bselzer.gradle.multiplatform.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

class MultiplatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(KotlinMultiplatformPlugin::class.java)

        val extension = multiplatformExtension {
            jdkVersion.convention(11)
        }

        with(extensions.getByType<KotlinMultiplatformExtension>()) {
            configureTargets()
            jvmToolchain(extension.jdkVersion.get())
        }

        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
            kotlinOptions.jvmTarget = extension.jdkVersion.get().toString()
        }
    }

    private fun KotlinMultiplatformExtension.configureTargets() {
        jvm()
        android {
            publishLibraryVariants("release", "debug")
        }
    }
}