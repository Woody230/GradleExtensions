package com.bselzer.gradle.kotlin.multiplatform.compose.plugin

import org.gradle.api.Project

val Project.multiplatformComposeExtension: MultiplatformComposeExtension
    get() = extensions.getByName("multiplatformComposeExtension") as MultiplatformComposeExtension

fun Project.multiplatformComposeExtension(configure: MultiplatformComposeExtension.() -> Unit) = multiplatformComposeExtension.apply(configure)