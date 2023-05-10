package com.bselzer.gradle.android.plugin

import org.gradle.api.Project

val Project.androidExtension: AndroidExtension
    get() = extensions.getByName("androidExtension") as AndroidExtension

fun Project.androidExtension(configure: AndroidExtension.() -> Unit) = androidExtension.apply(configure)