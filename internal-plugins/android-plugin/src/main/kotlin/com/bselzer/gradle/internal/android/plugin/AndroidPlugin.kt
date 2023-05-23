package com.bselzer.gradle.internal.android.plugin

import com.android.build.api.dsl.CommonExtension
import com.bselzer.gradle.function.toNumericString
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class AndroidPlugin : Plugin<Project> {
    protected abstract val Project.androidExtension: AndroidExtension

    protected abstract val Project.commonExtension: CommonExtension<*, *, *, *>

    override fun apply(project: Project): Unit = with(project) {
        val extension = androidExtension.apply {
            namespace.group.convention("com.bselzer")
            namespace.module.convention(name)
            compileSdk.convention(33)
            minSdk.convention(21)
            testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")
            sourceCompatibility.convention(JavaVersion.VERSION_11)
            targetCompatibility.convention(JavaVersion.VERSION_11)
        }

        with(commonExtension) {
            namespace = "${extension.namespace.group.get()}.${extension.namespace.category.get()}.${extension.namespace.module.get()}".replace("-", ".")
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
            kotlinOptions.jvmTarget = extension.targetCompatibility.get().toNumericString()
        }
    }
}