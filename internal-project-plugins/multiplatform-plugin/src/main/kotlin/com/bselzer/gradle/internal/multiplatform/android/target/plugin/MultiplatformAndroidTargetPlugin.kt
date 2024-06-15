package com.bselzer.gradle.internal.multiplatform.android.target.plugin

import com.bselzer.gradle.multiplatform.kotlinMultiplatformExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformAndroidTargetPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        kotlinMultiplatformExtension {
            androidTarget {
                publishLibraryVariants("release", "debug")
            }
        }
    }
}