package com.bselzer.gradle.multiplatform

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.commonMain: KotlinSourceSet
    get() = getByName("commonMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.commonMain(configure: KotlinSourceSet.() -> Unit) = commonMain.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.commonMainOrNull: KotlinSourceSet?
    get() = findByName("commonMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeCommonMain(configure: KotlinSourceSet.() -> Unit) = commonMainOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.commonTest: KotlinSourceSet
    get() = getByName("commonTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.commonTest(configure: KotlinSourceSet.() -> Unit) = commonTest.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.commonTestOrNull: KotlinSourceSet?
    get() = findByName("commonTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeCommonTest(configure: KotlinSourceSet.() -> Unit) = commonTestOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.jvmMain: KotlinSourceSet
    get() = getByName("jvmMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.jvmMain(configure: KotlinSourceSet.() -> Unit) = jvmMain.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.jvmMainOrNull: KotlinSourceSet?
    get() = findByName("jvmMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeJvmMain(configure: KotlinSourceSet.() -> Unit) = jvmMainOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.jvmTest: KotlinSourceSet
    get() = getByName("jvmTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.jvmTest(configure: KotlinSourceSet.() -> Unit) = jvmTest.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.jvmTestOrNull: KotlinSourceSet?
    get() = findByName("jvmTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeJvmTest(configure: KotlinSourceSet.() -> Unit) = jvmTestOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.androidMain: KotlinSourceSet
    get() = getByName("androidMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.androidMain(configure: KotlinSourceSet.() -> Unit) = androidMain.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.androidMainOrNull: KotlinSourceSet?
    get() = findByName("androidMain")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeAndroidMain(configure: KotlinSourceSet.() -> Unit) = androidMainOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.androidUnitTest: KotlinSourceSet
    get() = getByName("androidUnitTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.androidUnitTest(configure: KotlinSourceSet.() -> Unit) = androidUnitTest.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.androidUnitTestOrNull: KotlinSourceSet?
    get() = findByName("androidUnitTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeAndroidUnitTest(configure: KotlinSourceSet.() -> Unit) = androidUnitTestOrNull?.apply(configure)


val NamedDomainObjectContainer<KotlinSourceSet>.androidInstrumentedTest: KotlinSourceSet
    get() = getByName("androidInstrumentedTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.androidInstrumentedTest(configure: KotlinSourceSet.() -> Unit) = androidInstrumentedTest.apply(configure)
val NamedDomainObjectContainer<KotlinSourceSet>.androidInstrumentedTestOrNull: KotlinSourceSet?
    get() = findByName("androidInstrumentedTest")

fun NamedDomainObjectContainer<KotlinSourceSet>.maybeAndroidInstrumentedTest(configure: KotlinSourceSet.() -> Unit) = androidInstrumentedTestOrNull?.apply(configure)