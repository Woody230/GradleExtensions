package com.bselzer.gradle.internal.multiplatform.compose.test.plugin

import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformComposeTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeAndroidUnitTest {
                implementation(BuildConfiguration.libs_androidx_test_compose_ui)
                implementation(BuildConfiguration.libs_androidx_test_compose_ui_junit)
                implementation(BuildConfiguration.libs_androidx_test_compose_ui_manifest)
            }
        }
    }
}
