package com.bselzer.gradle.internal.multiplatform.test.plugin

import com.bselzer.gradle.multiplatform.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeCommonTest {
                implementation(libs.kotlin.test.asProvider())
                implementation(libs.kotlin.test.annotations)
                implementation(libs.kotlin.reflect)
            }
            maybeAndroidUnitTest {
                implementation(libs.kotlin.test.junit)
                implementation(libs.androidx.test.core)
                implementation(libs.androidx.test.junit)
                implementation(libs.androidx.test.runner)
                implementation(libs.robolectric)
                implementation(libs.kotlin.reflect)
            }
            maybeJvmTest {
                implementation(libs.kotlin.test.junit)
                implementation(libs.kotlin.reflect)
            }
        }
    }
}