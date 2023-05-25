package com.bselzer.gradle.android.configure.component

import com.android.build.api.variant.AndroidComponentsExtension

interface AndroidComponentsConfigurer<Components, CommonExtension> where Components : AndroidComponentsExtension<CommonExtension, *, *>, CommonExtension : com.android.build.api.dsl.CommonExtension<*, *, *, *> {
    fun finalizeDsl(configure: CommonExtension.() -> Unit)
    fun maybeFinalizeDsl(configure: CommonExtension.() -> Unit)
}