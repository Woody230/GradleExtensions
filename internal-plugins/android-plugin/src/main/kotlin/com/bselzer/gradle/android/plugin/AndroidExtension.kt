package com.bselzer.gradle.android.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.provider.Property

interface AndroidExtension {
    /**
     * The base id of the namespace.
     */
    val namespaceId: Property<String>

    /**
     * The specific category, denoting the type of modules.
     */
    val subNamespaceId: Property<String>

    /**
     * The name of the module.
     */
    val artifactId: Property<String>

    /**
     * The API level to compile against.
     */
    val compileSdk: Property<Int>

    /**
     * The minimum API level required.
     */
    val minSdk: Property<Int>

    /**
     * The language level of the java source code.
     */
    val sourceCompatibility: Property<JavaVersion>

    /**
     * The version of the generated Java bytecode.
     */
    val targetCompatibility: Property<JavaVersion>

    /**
     * The fully qualified class name of the test instrumentation runner.
     */
    val testInstrumentationRunner: Property<String>
}