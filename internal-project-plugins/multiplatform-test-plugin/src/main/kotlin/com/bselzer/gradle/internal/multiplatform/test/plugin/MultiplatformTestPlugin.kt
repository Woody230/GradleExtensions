package com.bselzer.gradle.internal.multiplatform.test.plugin

import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeCommonTest {
                // TODO libs.kotlin.test.asProvider()
                implementation("org.jetbrains.kotlin:kotlin-test-common:2.0.21")

                // TODO libs.kotlin.test.annotations
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:2.0.21")

                // TODO libs.kotlin.reflect
                implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
            }
            maybeAndroidUnitTest {
                // TODO libs.kotlin.test.junit
                implementation("org.jetbrains.kotlin:kotlin-test-junit:2.0.21")

                // TODO libs.kotlin.reflect
                implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")

                // TODO libs.androidx.test.core
                implementation("androidx.test:core:1.5.0")

                // TODO libs.androidx.test.junit
                implementation("androidx.test.ext:junit:1.1.5")

                // TODO libs.androidx.test.runner
                implementation("androidx.test:runner:1.5.2")

                // TODO libs.robolectric
                implementation("org.robolectric:robolectric:4.12.2")
            }
            maybeJvmTest {
                // TODO libs.kotlin.test.junit
                implementation("org.jetbrains.kotlin:kotlin-test-junit:2.0.21")

                // TODO libs.kotlin.reflect
                implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
            }
        }
    }
}