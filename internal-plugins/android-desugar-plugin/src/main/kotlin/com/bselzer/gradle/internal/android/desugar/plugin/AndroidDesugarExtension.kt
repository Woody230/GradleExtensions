package com.bselzer.gradle.internal.android.desugar.plugin

import org.gradle.api.provider.Provider

interface AndroidDesugarExtension {
    val version: Provider<String>
}