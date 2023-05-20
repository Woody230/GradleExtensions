package com.bselzer.gradle.android.plugin

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class AndroidPlugin : Plugin<Project> {
    protected abstract val Project.androidExtension: AndroidExtension

    protected abstract val Project.commonExtension: CommonExtension<*, *, *, *>

    override fun apply(project: Project): Unit = with(project) {
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

        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
            val target = extension.targetCompatibility.get()
            kotlinOptions.jvmTarget = when (target) {
                JavaVersion.VERSION_1_8 -> "1.8"
                JavaVersion.VERSION_1_9 -> "9"
                JavaVersion.VERSION_1_10 -> "10"
                JavaVersion.VERSION_11 -> "11"
                JavaVersion.VERSION_12 -> "12"
                JavaVersion.VERSION_13 -> "13"
                JavaVersion.VERSION_14 -> "14"
                JavaVersion.VERSION_15 -> "15"
                JavaVersion.VERSION_16 -> "16"
                JavaVersion.VERSION_17 -> "17"
                JavaVersion.VERSION_18 -> "18"
                JavaVersion.VERSION_19 -> "19"
                JavaVersion.VERSION_20 -> "20"
                JavaVersion.VERSION_21 -> "21"
                JavaVersion.VERSION_22 -> "22"
                JavaVersion.VERSION_23 -> "23"
                JavaVersion.VERSION_24 -> "24"
                else -> throw NotImplementedError("Unsupported JVM target $target")
            }
        }
    }
}