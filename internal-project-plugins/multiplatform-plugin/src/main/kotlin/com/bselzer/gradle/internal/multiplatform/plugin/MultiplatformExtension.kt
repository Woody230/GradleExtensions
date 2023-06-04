package com.bselzer.gradle.internal.multiplatform.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.provider.Property

interface MultiplatformExtension {
    val jdkVersion: Property<JavaVersion>
}