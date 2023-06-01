package com.bselzer.gradle.function.project

import org.gradle.api.Project
import org.gradle.api.invocation.Gradle

/**
 * Creates a sequence of the builds starting from the current build that iterates toward the root of the composite.
 */
fun Project.compositeBuildSequence(): Sequence<Gradle> = generateSequence(project.gradle) { build -> build.parent }

/**
 * The root build of the composite.
 */
val Project.compositeRootBuild: Gradle
    get() = compositeBuildSequence().last()

/**
 * The root project of the composite.
 */
val Project.compositeRootProject: Project
    get() = compositeRootBuild.rootProject