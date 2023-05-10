package com.bselzer.gradle.android.desugar.plugin

import org.gradle.api.Project

val Project.androidDesugarExtension: AndroidDesugarExtension
    get() = extensions.getByName("androidDesugarExtension") as AndroidDesugarExtension

fun Project.androidDesugarExtension(configure: AndroidDesugarExtension.() -> Unit) = androidDesugarExtension.apply(configure)