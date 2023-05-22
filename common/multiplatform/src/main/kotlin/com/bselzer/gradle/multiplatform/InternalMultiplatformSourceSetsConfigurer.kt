package com.bselzer.gradle.multiplatform

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal class InternalMultiplatformSourceSetsConfigurer<Receiver>(
    private val extension: KotlinProjectExtension,
    private val configure: KotlinSourceSet.(Receiver.() -> Unit) -> Unit
) : MultiplatformSourceSetsConfigurer<Receiver> {
    private val sourceSets: NamedDomainObjectContainer<KotlinSourceSet>
        get() = extension.sourceSets

    private fun KotlinSourceSet?.apply(configure: Receiver.() -> Unit) {
        if (this == null) {
            return
        }

        configure(configure)
    }

    private fun Collection<KotlinSourceSet>.apply(configure: Receiver.() -> Unit) {
        forEach { sourceSet -> sourceSet.apply(configure) }
    }

    override fun commonMain(configure: Receiver.() -> Unit) = sourceSets.commonMain.apply(configure)
    override fun commonTest(configure: Receiver.() -> Unit) = sourceSets.commonTest.apply(configure)
    override fun jvmMain(configure: Receiver.() -> Unit) = sourceSets.jvmMain.apply(configure)
    override fun jvmTest(configure: Receiver.() -> Unit) = sourceSets.jvmTest.apply(configure)
    override fun androidMain(configure: Receiver.() -> Unit) = sourceSets.androidMain.apply(configure)
    override fun androidUnitTest(configure: Receiver.() -> Unit) = sourceSets.androidUnitTest.apply(configure)
    override fun androidInstrumentedTest(configure: Receiver.() -> Unit) = sourceSets.androidInstrumentedTest.apply(configure)

    override fun maybeCommonMain(configure: Receiver.() -> Unit) = sourceSets.commonMainOrNull.apply(configure)
    override fun maybeCommonTest(configure: Receiver.() -> Unit) = sourceSets.commonTestOrNull.apply(configure)
    override fun maybeJvmMain(configure: Receiver.() -> Unit) = sourceSets.jvmMainOrNull.apply(configure)
    override fun maybeJvmTest(configure: Receiver.() -> Unit) = sourceSets.jvmTestOrNull.apply(configure)
    override fun maybeAndroidMain(configure: Receiver.() -> Unit) = sourceSets.androidMainOrNull.apply(configure)
    override fun maybeAndroidUnitTest(configure: Receiver.() -> Unit) = sourceSets.androidUnitTestOrNull.apply(configure)
    override fun maybeAndroidInstrumentedTest(configure: Receiver.() -> Unit) = sourceSets.androidInstrumentedTestOrNull.apply(configure)

    override fun allSourceSets(configure: Receiver.() -> Unit) = sourceSets.apply(configure)
    override fun mainSourceSets(configure: Receiver.() -> Unit) = extension.mainSourceSets.apply(configure)
    override fun testSourceSets(configure: Receiver.() -> Unit) = extension.testSourceSets.apply(configure)
}