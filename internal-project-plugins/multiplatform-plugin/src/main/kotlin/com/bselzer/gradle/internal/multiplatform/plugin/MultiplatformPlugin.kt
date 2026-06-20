package com.bselzer.gradle.internal.multiplatform.plugin

import com.bselzer.gradle.function.toInt
import com.bselzer.gradle.function.toJavaVersion
import com.bselzer.gradle.function.toNumericString
import com.bselzer.gradle.internal.version.catalog.libs
import com.bselzer.gradle.multiplatform.kotlinMultiplatformExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class MultiplatformPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = multiplatformExtension {
            jdkVersion.convention(libs.versions.java.jdk.requiredVersion.toJavaVersion())
        }

        afterEvaluate {
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                compilerOptions {
                    jvmTarget = JvmTarget.fromTarget(extension.jdkVersion.get().toNumericString())
                }
            }
        }

        pluginManager.apply(libs.plugins.multiplatform.get().pluginId)

        // TODO https://github.com/gradle/gradle/issues/26061
        // The value for property 'languageVersion' is final and cannot be changed any further
        // TODO persist jdk version as expected instead of using the default
        kotlinMultiplatformExtension {
            jvmToolchain(extension.jdkVersion.get().toInt())
        }
    }
}