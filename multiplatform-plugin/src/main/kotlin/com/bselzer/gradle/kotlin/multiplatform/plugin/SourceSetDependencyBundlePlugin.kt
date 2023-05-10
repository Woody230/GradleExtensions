package com.bselzer.gradle.kotlin.multiplatform.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class SourceSetDependencyBundlePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
        kotlinExtension.sourceSets.forEach { sourceSet ->
            sourceSet.dependencies {
                val name = sourceSet.name
                val implementationBundle = libs.findBundle("$name-implementation")
                if (implementationBundle.isPresent) {
                    implementation(implementationBundle)
                }

                val apiBundle = libs.findBundle("$name-api")
                if (apiBundle.isPresent) {
                    api(apiBundle)
                }
            }
        }
    }
}