package com.bselzer.gradle.android.library.desugar.plugin

import org.gradle.api.provider.Provider

interface AndroidLibraryDesugarExtension {
    val version: Provider<String>
}