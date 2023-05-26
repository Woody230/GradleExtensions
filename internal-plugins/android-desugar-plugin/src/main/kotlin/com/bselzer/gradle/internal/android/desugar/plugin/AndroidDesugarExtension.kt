package com.bselzer.gradle.internal.android.desugar.plugin

import org.gradle.api.provider.Property

interface AndroidDesugarExtension {
    val version: Property<String>
}