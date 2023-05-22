package com.bselzer.gradle.multiplatform

interface MultiplatformSourceSetsConfigurer<Receiver> {
    fun allSourceSets(configure: Receiver.() -> Unit)
    fun mainSourceSets(configure: Receiver.() -> Unit)
    fun testSourceSets(configure: Receiver.() -> Unit)

    fun commonMain(configure: Receiver.() -> Unit)
    fun commonTest(configure: Receiver.() -> Unit)
    fun jvmMain(configure: Receiver.() -> Unit)
    fun jvmTest(configure: Receiver.() -> Unit)
    fun androidMain(configure: Receiver.() -> Unit)
    fun androidUnitTest(configure: Receiver.() -> Unit)
    fun androidInstrumentedTest(configure: Receiver.() -> Unit)

    fun maybeCommonMain(configure: Receiver.() -> Unit)
    fun maybeCommonTest(configure: Receiver.() -> Unit)
    fun maybeJvmMain(configure: Receiver.() -> Unit)
    fun maybeJvmTest(configure: Receiver.() -> Unit)
    fun maybeAndroidMain(configure: Receiver.() -> Unit)
    fun maybeAndroidUnitTest(configure: Receiver.() -> Unit)
    fun maybeAndroidInstrumentedTest(configure: Receiver.() -> Unit)
}