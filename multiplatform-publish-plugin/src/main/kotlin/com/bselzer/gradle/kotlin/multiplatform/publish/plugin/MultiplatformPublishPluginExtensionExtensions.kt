package com.bselzer.gradle.kotlin.multiplatform.publish.plugin

import org.gradle.api.Project

val Project.publishExtension: MultiplatformPublishPluginExtension
    get() = extensions.getByName("publishExtension") as MultiplatformPublishPluginExtension

fun Project.publishExtension(configure: MultiplatformPublishPluginExtension.() -> Unit) = publishExtension.apply(configure)