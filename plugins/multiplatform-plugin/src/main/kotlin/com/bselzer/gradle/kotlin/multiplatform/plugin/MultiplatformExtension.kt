package com.bselzer.gradle.kotlin.multiplatform.plugin

import org.gradle.api.provider.Property

interface MultiplatformExtension {
    val jdkVersion: Property<Int>
}