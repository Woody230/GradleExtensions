package com.bselzer.gradle.multiplatform.configure.sourceset

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

class MultiplatformDependencies internal constructor(
    extension: KotlinProjectExtension
) : MultiplatformSourceSetsConfigurer<KotlinDependencyHandler> by InternalMultiplatformSourceSetsConfigurer(
    extension,
    { configure -> dependencies(configure) }
)

val Project.multiplatformDependencies: MultiplatformDependencies
    get() = MultiplatformDependencies(kotlinExtension)

fun Project.multiplatformDependencies(configure: MultiplatformDependencies.() -> Unit) = multiplatformDependencies.configure()