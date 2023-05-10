package com.bselzer.gradle.kotlin.multiplatform.compose.plugin

import com.android.build.gradle.LibraryExtension
import com.bselzer.gradle.kotlin.multiplatform.plugin.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(ComposePlugin::class.java)

        val extension = extensions.create<MultiplatformComposeExtension>("multiplatformComposeExtension")
        extension.compilerVersion.convention(libs.versions.multiplatform.compose.compiler.get())

        afterEvaluate {
            with(extensions.getByType<LibraryExtension>()) {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    // https://mvnrepository.com/artifact/org.jetbrains.compose.compiler/compiler
                    // https://github.com/JetBrains/compose-multiplatform/blob/master/gradle-plugins/compose/src/main/kotlin/org/jetbrains/compose/ComposeCompilerCompatibility.kt
                    kotlinCompilerExtensionVersion = extension.compilerVersion.get()
                }

                packaging {
                    resources.pickFirsts.apply {
                        add("META-INF/AL2.0")
                        add("META-INF/LGPL2.1")
                    }
                }
            }
        }
    }
}