package com.bselzer.gradle.kotlin.multiplatform.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMultiplatformPlugin

class MultiplatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(KotlinMultiplatformPlugin::class.java)

        with(extensions.getByType<KotlinMultiplatformExtension>()) {
            configureTargets()
        }

        plugins.apply(SourceSetDependencyBundlePlugin::class.java)

        val extension = extensions.create<MultiplatformExtension>("multiplatformExtension")
        extension.jdkVersion.convention(11)

        afterEvaluate {
            with(extensions.getByType<KotlinMultiplatformExtension>()) {
                jvmToolchain(extension.jdkVersion.get())
            }
        }
    }

    private fun KotlinMultiplatformExtension.configureTargets() {
        jvm()
        android {
            publishLibraryVariants("release", "debug")
        }
    }
}