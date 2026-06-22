package com.bselzer.gradle.internal.multiplatform.test.plugin

import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeCommonTest {
                implementation(BuildConfiguration.libs_kotlin_test)
                implementation(BuildConfiguration.libs_kotlin_test_annotations)
                implementation(BuildConfiguration.libs_kotlin_reflect)
            }
            maybeAndroidHostTest {
                implementation(BuildConfiguration.libs_kotlin_test_junit)
                implementation(BuildConfiguration.libs_kotlin_reflect)
                implementation(BuildConfiguration.libs_androidx_test_core)
                implementation(BuildConfiguration.libs_androidx_test_junit)
                implementation(BuildConfiguration.libs_androidx_test_runner)
                implementation(BuildConfiguration.libs_robolectric)
            }
            maybeJvmTest {
                implementation(BuildConfiguration.libs_kotlin_test_junit)
                implementation(BuildConfiguration.libs_kotlin_reflect)
            }
        }
    }
}
