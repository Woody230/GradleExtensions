package com.bselzer.gradle.internal.kotlininject.plugin

import com.bselzer.gradle.multiplatform.multiplatformDependencies
import com.bselzer.gradle.multiplatform.multiplatformSourceSets
import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class KotlinInjectPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        plugins.apply(KspGradleSubplugin::class.java)
        configureMultiplatform()
    }

    private fun Project.configureMultiplatform() {
        if (!plugins.hasPlugin(libs.plugins.multiplatform.get().pluginId)) {
            return
        }

        dependencies.add("kspCommonMainMetadata", libs.kotlininject.ksp)
        multiplatformDependencies {
            mainSourceSets {
                implementation(libs.kotlininject.runtime)
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