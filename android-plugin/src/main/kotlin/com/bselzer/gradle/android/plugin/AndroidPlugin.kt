package com.bselzer.gradle.android.plugin

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType

class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(LibraryPlugin::class.java)

        val extension = extensions.create<AndroidExtension>("androidExtension")
        extension.namespaceId.convention("com.bselzer")
        extension.compileSdk.convention(33)
        extension.minSdk.convention(21)
        extension.testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")
        extension.sourceCompatability.convention(JavaVersion.VERSION_11)
        extension.targetCompatability.convention(JavaVersion.VERSION_11)

        afterEvaluate {
            with(extensions.getByType<LibraryExtension>()) {
                namespace = "${extension.namespaceId.get()}.${extension.subNamespaceId.get()}.${project.name}".replace("-", ".")
                compileSdk = extension.compileSdk.get()
                defaultConfig {
                    minSdk = extension.minSdk.get()
                    testInstrumentationRunner = extension.testInstrumentationRunner.get()
                }
                compileOptions {
                    sourceCompatibility = extension.sourceCompatability.get()
                    targetCompatibility = extension.targetCompatability.get()
                }
                testOptions {
                    unitTests {
                        androidResources {
                            isIncludeAndroidResources = true
                        }
                    }
                }
            }
        }
    }
}