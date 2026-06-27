package com.bselzer.gradle.multiplatform

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.commonMain: KotlinSourceSet
    get() = getByName("commonMain")

val NamedDomainObjectContainer<KotlinSourceSet>.commonMainOrNull: KotlinSourceSet?
    get() = findByName("commonMain")

val NamedDomainObjectContainer<KotlinSourceSet>.commonTest: KotlinSourceSet
    get() = getByName("commonTest")

val NamedDomainObjectContainer<KotlinSourceSet>.commonTestOrNull: KotlinSourceSet?
    get() = findByName("commonTest")

val NamedDomainObjectContainer<KotlinSourceSet>.jvmMain: KotlinSourceSet
    get() = getByName("jvmMain")

val NamedDomainObjectContainer<KotlinSourceSet>.jvmMainOrNull: KotlinSourceSet?
    get() = findByName("jvmMain")

val NamedDomainObjectContainer<KotlinSourceSet>.jvmTest: KotlinSourceSet
    get() = getByName("jvmTest")

val NamedDomainObjectContainer<KotlinSourceSet>.jvmTestOrNull: KotlinSourceSet?
    get() = findByName("jvmTest")

val NamedDomainObjectContainer<KotlinSourceSet>.androidMain: KotlinSourceSet
    get() = getByName("androidMain")

val NamedDomainObjectContainer<KotlinSourceSet>.androidMainOrNull: KotlinSourceSet?
    get() = findByName("androidMain")

val NamedDomainObjectContainer<KotlinSourceSet>.androidHostTest: KotlinSourceSet
    get() = getByName("androidHostTest")

val NamedDomainObjectContainer<KotlinSourceSet>.androidHostTestOrNull: KotlinSourceSet?
    get() = findByName("androidHostTest")

val NamedDomainObjectContainer<KotlinSourceSet>.androidDeviceTest: KotlinSourceSet
    get() = getByName("androidDeviceTest")

val NamedDomainObjectContainer<KotlinSourceSet>.androidDeviceTestOrNull: KotlinSourceSet?
    get() = findByName("androidDeviceTest")

val KotlinProjectExtension.mainSourceSets: Collection<KotlinSourceSet>
    get() = sourceSets.filter { sourceSet -> sourceSet.name == "main" || sourceSet.name.endsWith("Main") }

val KotlinProjectExtension.testSourceSets: Collection<KotlinSourceSet>
    get() = sourceSets.filter { sourceSet -> sourceSet.name == "test" || sourceSet.name.endsWith("Test") }