package com.bselzer.gradle.multiplatform

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class MultiplatformSourceSets(
    private val extension: KotlinProjectExtension
) : MultiplatformSourceSetsConfigurer<KotlinSourceSet> by InternalMultiplatformSourceSetsConfigurer(
    extension.sourceSets,
    { configure -> apply(configure) }
)

fun Project.multiplatformSourceSets(configure: MultiplatformSourceSets.() -> Unit) = MultiplatformSourceSets(kotlinExtension).apply(configure)