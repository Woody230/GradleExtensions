package com.bselzer.gradle.multiplatform

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

class MultiplatformDependencies(
    private val extension: KotlinProjectExtension
) : MultiplatformSourceSetsConfigurer<KotlinDependencyHandler> by InternalMultiplatformSourceSetsConfigurer(
    extension.sourceSets,
    { configure -> dependencies(configure) }
)

fun Project.multiplatformDependencies(configure: MultiplatformDependencies.() -> Unit) = MultiplatformDependencies(kotlinExtension).apply(configure)