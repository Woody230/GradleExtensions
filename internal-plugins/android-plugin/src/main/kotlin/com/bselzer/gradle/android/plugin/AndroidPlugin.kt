package com.bselzer.gradle.android.plugin

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(LibraryPlugin::class.java)

        val extension = androidExtension {
            namespaceId.convention("com.bselzer")
            artifactId.convention(name)
            compileSdk.convention(33)
            minSdk.convention(21)
            testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")
            sourceCompatibility.convention(JavaVersion.VERSION_11)
            targetCompatibility.convention(JavaVersion.VERSION_11)
        }

        with(extensions.getByType<LibraryExtension>()) {
            namespace = "${extension.namespaceId.get()}.${extension.subNamespaceId.get()}.${extension.artifactId.get()}".replace("-", ".")
            compileSdk = extension.compileSdk.get()
            defaultConfig {
                minSdk = extension.minSdk.get()
                testInstrumentationRunner = extension.testInstrumentationRunner.get()
            }
            compileOptions {
                sourceCompatibility = extension.sourceCompatibility.get()
                targetCompatibility = extension.targetCompatibility.get()
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