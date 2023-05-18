package com.bselzer.gradle.project.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories

class ProjectPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        allprojects {
            repositories {
                google()
                mavenCentral()
                gradlePluginPortal()
            }
        }
    }
}