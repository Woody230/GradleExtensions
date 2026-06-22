package com.bselzer.gradle.multiplatform.configure.sourceset

import com.bselzer.gradle.multiplatform.*
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal class InternalMultiplatformSourceSetsConfigurer<Receiver>(
    private val extension: KotlinProjectExtension,
    private val configure: KotlinSourceSet.(Receiver.() -> Unit) -> Unit
) : MultiplatformSourceSetsConfigurer<Receiver> {
    private val sourceSets: NamedDomainObjectContainer<KotlinSourceSet>
        get() = extension.sourceSets

    private fun KotlinSourceSet?.apply(name: String, configure: Receiver.() -> Unit) {
        if (this == null) {
            extension.project.logger.lifecycle("Unable to configure KotlinSourceSet $name because it does not exist.")
            return
        }

        apply(configure)
    }

    private fun KotlinSourceSet.apply(configure: Receiver.() -> Unit) {
        extension.project.logger.lifecycle("Configuring KotlinSourceSet $name")
        configure(configure)
    }

    private fun Collection<KotlinSourceSet>.apply(configure: Receiver.() -> Unit) {
        forEach { sourceSet -> sourceSet.apply(sourceSet.name, configure) }
    }

    override fun commonMain(configure: Receiver.() -> Unit) = sourceSets.commonMain.apply(configure)
    override fun commonTest(configure: Receiver.() -> Unit) = sourceSets.commonTest.apply(configure)
    override fun jvmMain(configure: Receiver.() -> Unit) = sourceSets.jvmMain.apply(configure)
    override fun jvmTest(configure: Receiver.() -> Unit) = sourceSets.jvmTest.apply(configure)
    override fun androidMain(configure: Receiver.() -> Unit) = sourceSets.androidMain.apply(configure)
    override fun androidHostTest(configure: Receiver.() -> Unit) = sourceSets.androidHostTest.apply(configure)
    override fun androidDeviceTest(configure: Receiver.() -> Unit) = sourceSets.androidDeviceTest.apply(configure)

    override fun maybeCommonMain(configure: Receiver.() -> Unit) = sourceSets.commonMainOrNull.apply(name = "commonMain", configure)
    override fun maybeCommonTest(configure: Receiver.() -> Unit) = sourceSets.commonTestOrNull.apply(name = "commonTest", configure)
    override fun maybeJvmMain(configure: Receiver.() -> Unit) = sourceSets.jvmMainOrNull.apply(name = "jvmMain", configure)
    override fun maybeJvmTest(configure: Receiver.() -> Unit) = sourceSets.jvmTestOrNull.apply(name = "jvmTest", configure)
    override fun maybeAndroidMain(configure: Receiver.() -> Unit) = sourceSets.androidMainOrNull.apply(name = "androidMain", configure)
    override fun maybeAndroidHostTest(configure: Receiver.() -> Unit) = sourceSets.androidHostTestOrNull.apply(name = "androidHostTest", configure)
    override fun maybeAndroidDeviceTest(configure: Receiver.() -> Unit) = sourceSets.androidDeviceTestOrNull.apply(name = "androidDeviceTest", configure)

    override fun allSourceSets(configure: Receiver.() -> Unit) = sourceSets.apply(configure)
    override fun mainSourceSets(configure: Receiver.() -> Unit) = extension.mainSourceSets.apply(configure)
    override fun testSourceSets(configure: Receiver.() -> Unit) = extension.testSourceSets.apply(configure)
}