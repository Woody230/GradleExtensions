package com.bselzer.gradle.function.gradle

import org.gradle.api.invocation.Gradle

/**
 * Creates a sequence of the Gradle instance starting from the given instance that iterates toward the root of the composite.
 */
fun Gradle.compositeSequence(): Sequence<Gradle> = generateSequence(this) { build -> build.parent }

/**
 * The root Gradle instance of the composite.
 */
val Gradle.compositeRoot: Gradle
    get() = compositeSequence().last()

/**
 * Whether this Gradle instance is the root of the composite.
 */
val Gradle.isCompositeRoot: Boolean
    get() = parent == null