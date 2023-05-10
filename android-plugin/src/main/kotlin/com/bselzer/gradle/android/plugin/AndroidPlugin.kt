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

        // TODO extension
        val subgroupId = extensions.getByName("subgroupId")

        with(extensions.getByType<LibraryExtension>()) {
            namespace = "com.bselzer.$subgroupId.${project.name}".replace("-", ".")
            compileSdk = 33
            defaultConfig {
                minSdk = 21
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
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