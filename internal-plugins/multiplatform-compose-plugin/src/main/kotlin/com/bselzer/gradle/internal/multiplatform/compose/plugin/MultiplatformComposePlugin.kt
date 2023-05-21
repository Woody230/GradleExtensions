package com.bselzer.gradle.internal.multiplatform.compose.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.internal.android.androidExtensionOrNull
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.compose.ComposePlugin

class MultiplatformComposePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(ComposePlugin::class.java)

        val extension = multiplatformComposeExtension {
            compilerVersion.convention(libs.versions.androidx.compose.get())
        }

        androidExtensionOrNull?.configureCompose(extension)
    }

    private fun CommonExtension<*, *, *, *>.configureCompose(
        extension: MultiplatformComposeExtension
    ) {
        buildFeatures {
            compose = true
        }
        composeOptions {
            // https://github.com/JetBrains/compose-multiplatform/blob/master/VERSIONING.md#kotlin-compatibility
            // https://github.com/JetBrains/compose-multiplatform/blob/master/gradle-plugins/compose/src/main/kotlin/org/jetbrains/compose/ComposeCompilerCompatibility.kt
            // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
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