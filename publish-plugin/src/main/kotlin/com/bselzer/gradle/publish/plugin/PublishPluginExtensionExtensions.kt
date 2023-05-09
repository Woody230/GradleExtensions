package com.bselzer.gradle.publish.plugin

import org.gradle.api.Project

val Project.publishExtension: PublishPluginExtension
    get() = extensions.getByName("publishExtension") as PublishPluginExtension

fun Project.publishExtension(configure: PublishPluginExtension.() -> Unit) = publishExtension.apply(configure)