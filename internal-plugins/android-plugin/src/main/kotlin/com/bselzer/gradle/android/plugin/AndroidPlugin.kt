package com.bselzer.gradle.android.plugin

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class AndroidPlugin : Plugin<Project> {
    protected abstract val Project.androidExtension: AndroidExtension

    protected abstract val Project.commonExtension: CommonExtension<*, *, *, *>

    override fun apply(project: Project) = with(project) {
        val extension = androidExtension.apply {
            namespaceId.convention("com.bselzer")
            artifactId.convention(name)
            compileSdk.convention(33)
            minSdk.convention(21)
            testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")
            sourceCompatibility.convention(JavaVersion.VERSION_11)
            targetCompatibility.convention(JavaVersion.VERSION_11)
        }

        with(commonExtension) {
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