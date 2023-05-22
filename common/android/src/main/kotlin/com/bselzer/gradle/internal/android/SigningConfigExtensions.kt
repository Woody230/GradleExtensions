package com.bselzer.gradle.internal.android

import com.android.build.api.dsl.ApkSigningConfig
import org.gradle.api.NamedDomainObjectContainer

val NamedDomainObjectContainer<out ApkSigningConfig>.release: ApkSigningConfig
    get() = getByName("release")

fun NamedDomainObjectContainer<out ApkSigningConfig>.release(configure: ApkSigningConfig.() -> Unit): ApkSigningConfig {
    return findByName("release")?.apply(configure) ?: create("release", configure)
}
