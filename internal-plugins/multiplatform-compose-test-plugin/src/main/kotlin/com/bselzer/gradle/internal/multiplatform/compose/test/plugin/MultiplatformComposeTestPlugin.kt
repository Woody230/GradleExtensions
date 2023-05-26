package com.bselzer.gradle.internal.multiplatform.compose.test.plugin

import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposeTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeAndroidUnitTest {
                // TODO libs.androidx.test.compose.ui.asProvider()
                implementation("androidx.compose.ui:ui-test:1.4.2")

                // TODO libs.androidx.test.compose.ui.junit
                implementation("androidx.compose.ui:ui-test-junit4:1.4.2")

                // TODO libs.androidx.test.compose.ui.manifest
                implementation("androidx.compose.ui:ui-test-manifest:1.4.2")
            }
        }
    }
}