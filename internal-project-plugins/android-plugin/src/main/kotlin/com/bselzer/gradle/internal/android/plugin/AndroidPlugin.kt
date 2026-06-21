package com.bselzer.gradle.internal.android.plugin

import com.bselzer.gradle.android.commonDslAndroidComponentsExtension
import com.bselzer.gradle.android.finalizeDslReceiver
import com.bselzer.gradle.function.toJavaVersion
import com.bselzer.gradle.function.toNumericString
import com.bselzer.gradle.internal.named.version.catalog.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

abstract class AndroidPlugin : Plugin<Project> {
    protected abstract val Project.androidExtension: AndroidExtension

    override fun apply(project: Project): Unit = with(project) {
        val extension = androidExtension.apply {
            namespace.group.convention("com.bselzer")
            namespace.module.convention(name)

            compileSdk.convention(libs.versions.android.compileSdk.requiredVersion.toInt())
            minSdk.convention(libs.versions.android.minSdk.requiredVersion.toInt())

            testInstrumentationRunner.convention("androidx.test.runner.AndroidJUnitRunner")

            sourceCompatibility.convention(libs.versions.java.sourceCompatibility.requiredVersion.toJavaVersion())
            targetCompatibility.convention(libs.versions.java.targetCompatibility.requiredVersion.toJavaVersion())

            buildConfig.convention(false)
        }

        // NOTE: Must configure in finalizeDsl not afterEvaluate
        // https://developer.android.com/build/extend-agp#build-flow-extension-points
        commonDslAndroidComponentsExtension.finalizeDslReceiver {
            namespace = "${extension.namespace.group.get()}.${extension.namespace.category.get()}.${extension.namespace.module.get()}".replace("-", ".")
            compileSdk = extension.compileSdk.get()

            with (defaultConfig) {
                minSdk = extension.minSdk.get()
                testInstrumentationRunner = extension.testInstrumentationRunner.get()
            }

            buildFeatures.buildConfig = extension.buildConfig.get()

            with (compileOptions) {
                sourceCompatibility = extension.sourceCompatibility.get()
                targetCompatibility = extension.targetCompatibility.get()
            }

            testOptions.unitTests.isIncludeAndroidResources = true
        }

        afterEvaluate {
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                compilerOptions.jvmTarget = JvmTarget.fromTarget(extension.targetCompatibility.get().toNumericString())
            }
        }
    }
}