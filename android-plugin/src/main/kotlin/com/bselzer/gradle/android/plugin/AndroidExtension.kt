package com.bselzer.gradle.android.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.provider.Property

interface AndroidExtension {
    val namespaceId: Property<String>
    val subNamespaceId: Property<String>
    val compileSdk: Property<Int>
    val minSdk: Property<Int>
    val sourceCompatability: Property<JavaVersion>
    val targetCompatability: Property<JavaVersion>
    val testInstrumentationRunner: Property<String>
}