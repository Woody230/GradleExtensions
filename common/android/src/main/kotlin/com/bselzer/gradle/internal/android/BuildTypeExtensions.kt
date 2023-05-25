package com.bselzer.gradle.internal.android

import com.android.build.api.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer

val <T : BuildType> NamedDomainObjectContainer<T>.debug: T
    get() = getByName("debug")

fun <T : BuildType> NamedDomainObjectContainer<T>.debug(configure: T.() -> Unit) = debug.apply(configure)

val <T : BuildType> NamedDomainObjectContainer<T>.release: T
    get() = getByName("release")

fun <T : BuildType> NamedDomainObjectContainer<T>.release(configure: T.() -> Unit) = release.apply(configure)

