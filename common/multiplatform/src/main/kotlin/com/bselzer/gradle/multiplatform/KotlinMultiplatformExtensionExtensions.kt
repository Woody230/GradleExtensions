package com.bselzer.gradle.multiplatform

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = extensions.getByType(KotlinMultiplatformExtension::class.java)

fun Project.kotlinMultiplatformExtension(configure: KotlinMultiplatformExtension.() -> Unit) = kotlinMultiplatformExtension.configure()

val Project.kotlinMultiplatformExtensionOrNull: KotlinMultiplatformExtension?
    get() = extensions.findByType(KotlinMultiplatformExtension::class.java)

fun Project.maybeKotlinMultiplatformExtension(configure: KotlinMultiplatformExtension.() -> Unit) = kotlinMultiplatformExtensionOrNull?.configure()