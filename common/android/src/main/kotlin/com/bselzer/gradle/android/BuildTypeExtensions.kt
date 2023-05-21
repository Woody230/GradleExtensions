package com.bselzer.gradle.android

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer


val NamedDomainObjectContainer<out BuildType>.debug: ApplicationBuildType
    get() = getByName("debug")

fun NamedDomainObjectContainer<out BuildType>.debug(configure: ApplicationBuildType.() -> Unit) = debug.apply(configure)


val NamedDomainObjectContainer<out BuildType>.release: ApplicationBuildType
    get() = getByName("release")

fun NamedDomainObjectContainer<out BuildType>.release(configure: ApplicationBuildType.() -> Unit) = release.apply(configure)

