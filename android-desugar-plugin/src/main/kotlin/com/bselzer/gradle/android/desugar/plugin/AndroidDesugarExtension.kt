package com.bselzer.gradle.android.desugar.plugin

import org.gradle.api.provider.Provider

interface AndroidDesugarExtension {
    val version: Provider<String>
}