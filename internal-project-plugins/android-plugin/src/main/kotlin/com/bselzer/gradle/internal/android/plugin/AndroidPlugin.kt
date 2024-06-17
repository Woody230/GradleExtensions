package com.bselzer.gradle.internal.android.plugin

import com.bselzer.gradle.android.androidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.function.toNumericString
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

abstract class AndroidPlugin : Plugin<Project> {
    protected abstract val Project.androidExtension: AndroidExtension

    override fun apply(project: Project): Unit = with(project) {
        val extension = androidExtension.apply {
            namespace.group.convention("com.bselzer")
            namespace.module.convention(name)

            // TODO libs.versions.android.compileSdk.get().toInt()
            compileSdk.convention(34)

            // TODO libs.versions.android.minSdk.get().toInt()
            minSdk.convention(21)

            testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")

            // TODO libs.versions.java.sourceCompatibility.get().toJavaVersion()
            sourceCompatibility.convention(JavaVersion.VERSION_11)

            // TODO libs.versions.java.targetCompatability.get().toJavaVersion()
            targetCompatibility.convention(JavaVersion.VERSION_11)
            buildConfig.convention(false)
        }

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        androidComponentsExtension.finalizeDslReceiver {
            namespace = "${extension.namespace.group.get()}.${extension.namespace.category.get()}.${extension.namespace.module.get()}".replace("-", ".")
            compileSdk = extension.compileSdk.get()
            defaultConfig {
                minSdk = extension.minSdk.get()
                testInstrumentationRunner = extension.testInstrumentationRunner.get()
            }
            buildFeatures {
                buildConfig = extension.buildConfig.get()
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

        afterEvaluate {
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                compilerOptions.jvmTarget = JvmTarget.fromTarget(extension.targetCompatibility.get().toNumericString())
            }
        }
    }
}