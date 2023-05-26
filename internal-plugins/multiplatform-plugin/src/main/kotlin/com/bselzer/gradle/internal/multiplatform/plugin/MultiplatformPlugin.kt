package com.bselzer.gradle.internal.multiplatform.plugin

import com.bselzer.gradle.function.toInt
import com.bselzer.gradle.function.toNumericString
import com.bselzer.gradle.multiplatform.kotlinMultiplatformExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = multiplatformExtension {
            jdkVersion.convention(JavaVersion.VERSION_11)
        }

        afterEvaluate {
            kotlinMultiplatformExtension {
                jvmToolchain(extension.jdkVersion.get().toInt())
            }

            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = extension.jdkVersion.get().toNumericString()
            }
        }

        // TODO libs.plugins.multiplatform.get().pluginId
        plugins.apply("org.jetbrains.kotlin.multiplatform")

        kotlinMultiplatformExtension {
            configureTargets()
        }
    }

    private fun KotlinMultiplatformExtension.configureTargets() {
        jvm()
        android {
            publishLibraryVariants("release", "debug")
        }
    }
}