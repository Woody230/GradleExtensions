package com.bselzer.gradle.multiplatform

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

class MultiplatformDependencies(
    private val extension: KotlinProjectExtension
) {
    fun commonMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.commonMain.dependencies(configure)
    fun commonTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.commonTest.dependencies(configure)
    fun jvmMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.jvmMain.dependencies(configure)
    fun jvmTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.jvmTest.dependencies(configure)
    fun androidMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidMain.dependencies(configure)
    fun androidUnitTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidUnitTest.dependencies(configure)
    fun androidInstrumentedTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidInstrumentedTest.dependencies(configure)

    fun maybeCommonMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.commonMainOrNull?.dependencies(configure)
    fun maybeCommonTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.commonTestOrNull?.dependencies(configure)
    fun maybeJvmMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.jvmMainOrNull?.dependencies(configure)
    fun maybeJvmTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.jvmTestOrNull?.dependencies(configure)
    fun maybeAndroidMain(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidMainOrNull?.dependencies(configure)
    fun maybeAndroidUnitTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidUnitTestOrNull?.dependencies(configure)
    fun maybeAndroidInstrumentedTest(configure: KotlinDependencyHandler.() -> Unit) = extension.sourceSets.androidInstrumentedTestOrNull?.dependencies(configure)
}

fun Project.multiplatformDependencies(configure: MultiplatformDependencies.() -> Unit) = MultiplatformDependencies(kotlinExtension).apply(configure)