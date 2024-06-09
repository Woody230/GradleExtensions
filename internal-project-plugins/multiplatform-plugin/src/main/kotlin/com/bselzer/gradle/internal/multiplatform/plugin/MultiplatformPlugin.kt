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
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = extension.jdkVersion.get().toNumericString()
            }
        }

        // TODO libs.plugins.multiplatform.get().pluginId
        plugins.apply("org.jetbrains.kotlin.multiplatform")

        // TODO https://github.com/gradle/gradle/issues/26061
        // The value for property 'languageVersion' is final and cannot be changed any further
        // TODO persist jdk version as expected instead of using the default
        kotlinMultiplatformExtension {
            jvmToolchain(extension.jdkVersion.get().toInt())
        }

        kotlinMultiplatformExtension {
            configureTargets()
        }
    }

    private fun KotlinMultiplatformExtension.configureTargets() {
        jvm()
        androidTarget {
            publishLibraryVariants("release", "debug")
        }
    }
}