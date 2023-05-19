package com.bselzer.gradle.multiplatform.compose.test.plugin

import com.bselzer.gradle.multiplatform.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposeTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            androidUnitTest {
                implementation(libs.androidx.test.compose.ui.asProvider())
                implementation(libs.androidx.test.compose.ui.junit)
                implementation(libs.androidx.test.compose.ui.manifest)
            }
        }
    }
}