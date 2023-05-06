package com.bselzer.gradle.kotlin.multiplatform

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/**
 * Sets up common dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.commonMain(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("commonMain") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up common test dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.commonTest(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("commonTest") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up JVM dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.jvmMain(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("jvmMain") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up JVM test dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.jvmTest(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("jvmTest") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up Android dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.androidMain(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("androidMain") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up Android unit test dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.androidUnitTest(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("androidUnitTest") {
        it.dependencies {
            block(this)
        }
    }
}

/**
 * Sets up Android instrumented test dependencies.
 */
fun NamedDomainObjectContainer<KotlinSourceSet>.androidInstrumentedTest(block: KotlinDependencyHandler.() -> Unit = {}) {
    getByName("androidInstrumentedTest") {
        it.dependencies {
            block(this)
        }
    }
}