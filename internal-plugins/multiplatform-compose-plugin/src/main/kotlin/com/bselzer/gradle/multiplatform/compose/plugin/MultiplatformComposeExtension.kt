package com.bselzer.gradle.multiplatform.compose.plugin

import org.gradle.api.provider.Property

interface MultiplatformComposeExtension {
    val compilerVersion: Property<String>
}