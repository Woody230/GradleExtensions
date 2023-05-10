package com.bselzer.gradle.kotlin.multiplatform.plugin

import org.gradle.api.Project

val Project.multiplatformExtension: MultiplatformExtension
    get() = extensions.getByName("multiplatformExtension") as MultiplatformExtension

fun Project.multiplatformExtension(configure: MultiplatformExtension.() -> Unit) = multiplatformExtension.apply(configure)