package com.bselzer.gradle.internal.android.plugin

import com.bselzer.gradle.internal.models.ModuleId
import org.gradle.api.JavaVersion
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

interface AndroidExtension {
    /**
     * The id of the namespace.
     */
    @get:Nested
    val namespace: ModuleId

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

    /**
     * Whether the build config is enabled.
     */
    val buildConfig: Property<Boolean>
}