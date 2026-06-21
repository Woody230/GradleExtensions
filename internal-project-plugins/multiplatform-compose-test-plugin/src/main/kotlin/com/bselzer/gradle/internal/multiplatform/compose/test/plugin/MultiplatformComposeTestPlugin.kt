package com.bselzer.gradle.internal.multiplatform.compose.test.plugin

import com.bselzer.gradle.internal.named.version.catalog.libs
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposeTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeAndroidUnitTest {
                implementation(libs.androidx.test.compose.ui.asProvider())
                implementation(libs.androidx.test.compose.ui.junit)
                implementation(libs.androidx.test.compose.ui.manifest)
            }
        }
    }
}