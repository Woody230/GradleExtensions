package com.bselzer.gradle.internal.multiplatform.test.plugin

import com.bselzer.gradle.android.multiplatformLibraryAndroidComponentsExtension
import io.github.woody230.gradle.internal.build.configuration.BuildConfiguration
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.testing.AbstractTestTask
import org.gradle.kotlin.dsl.register

class MultiplatformTestPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        multiplatformDependencies {
            maybeCommonTest {
                logger.info("Adding the multiplatform test dependencies for commonTest.")
                implementation(BuildConfiguration.libs_kotlin_test)
                implementation(BuildConfiguration.libs_kotlin_test_annotations)
                implementation(BuildConfiguration.libs_kotlin_reflect)
            }
            maybeAndroidHostTest {
                logger.info("Adding the multiplatform test dependencies for androidHostTest.")
                implementation(BuildConfiguration.libs_kotlin_test_junit)
                implementation(BuildConfiguration.libs_kotlin_reflect)
                implementation(BuildConfiguration.libs_androidx_test_core)
                implementation(BuildConfiguration.libs_androidx_test_junit)
                implementation(BuildConfiguration.libs_androidx_test_runner)
                implementation(BuildConfiguration.libs_robolectric).also { configureRobolectric() }
            }
            maybeJvmTest {
                logger.info("Adding the multiplatform test dependencies for jvmTest.")
                implementation(BuildConfiguration.libs_kotlin_test_junit)
                implementation(BuildConfiguration.libs_kotlin_reflect)
            }
        }

        // NOTE: https://github.com/gradle/gradle/issues/33619
        tasks.withType(AbstractTestTask::class.java).configureEach {
            failOnNoDiscoveredTests.set(false)
        }
    }

    private fun Project.configureRobolectric() {
        // TODO https://github.com/robolectric/robolectric/pull/9680#issuecomment-4621865028
        val generateRobolectricPropertiesTask = project.tasks.register<GenerateRobolectricPropertiesTask>("generateRobolectricProperties") {
            sdk.set(BuildConfiguration.versions_android_robolectric_sdk)
        }

        multiplatformLibraryAndroidComponentsExtension.onVariants { variant ->
            variant.hostTests.values.forEach { hostTest ->
                hostTest.sources.resources?.addGeneratedSourceDirectory(
                    taskProvider = generateRobolectricPropertiesTask,
                    wiredWith = GenerateRobolectricPropertiesTask::outputDir
                )
            }
        }
    }

    @CacheableTask
    internal abstract class GenerateRobolectricPropertiesTask : DefaultTask() {
        @get:Input
        abstract val sdk: Property<Int>
        @get:OutputDirectory
        abstract val outputDir: DirectoryProperty

        @TaskAction
        fun generate() = outputDir.get().asFile
            .also { directory ->
                logger.debug("robolectric.properties directory: ${directory.absolutePath}")
                directory.mkdirs()
            }
            .resolve("robolectric.properties")
            .writeText("sdk=${sdk.get()}")
    }
}
