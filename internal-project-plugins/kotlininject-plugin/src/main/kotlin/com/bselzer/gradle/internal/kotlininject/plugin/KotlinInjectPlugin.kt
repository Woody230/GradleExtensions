package com.bselzer.gradle.internal.kotlininject.plugin

import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformDependencies
import com.bselzer.gradle.multiplatform.configure.sourceset.multiplatformSourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class KotlinInjectPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        // TODO libs.ksp.get().pluginId
        plugins.apply("com.google.devtools.ksp")
        configureMultiplatform()
    }

    private fun Project.configureMultiplatform() {
        // TODO libs.plugins.multiplatform.get().pluginId
        if (!plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
            return
        }

        // TODO libs.kotlininject.ksp
        dependencies.add("kspCommonMainMetadata", "me.tatarka.inject:kotlin-inject-compiler-ksp:0.6.3")
        multiplatformDependencies {
            mainSourceSets {
                // TODO libs.kotlininject.runtime
                implementation("me.tatarka.inject:kotlin-inject-runtime:0.6.3")
            }
        }

        // TODO https://github.com/google/ksp/issues/567
        // KSP will generate per-platform, but want to be able to access in common.
        multiplatformSourceSets {
            commonMain {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}