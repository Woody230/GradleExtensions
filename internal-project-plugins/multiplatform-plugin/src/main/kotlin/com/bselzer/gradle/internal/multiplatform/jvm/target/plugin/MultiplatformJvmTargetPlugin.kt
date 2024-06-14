package com.bselzer.gradle.internal.multiplatform.jvm.target.plugin

import com.bselzer.gradle.multiplatform.kotlinMultiplatformExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformJvmTargetPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        kotlinMultiplatformExtension {
            jvm()
        }
    }
}