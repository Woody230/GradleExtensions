package com.bselzer.gradle.multiplatform.configure.sourceset

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class MultiplatformSourceSets(
    extension: KotlinProjectExtension
) : MultiplatformSourceSetsConfigurer<KotlinSourceSet> by InternalMultiplatformSourceSetsConfigurer(
    extension,
    { configure -> apply(configure) }
)

fun Project.multiplatformSourceSets(configure: MultiplatformSourceSets.() -> Unit) = MultiplatformSourceSets(kotlinExtension).apply(configure)