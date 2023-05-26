package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlin.jvm")
    id("io.github.woody230.gradle.convention.publish")
}

mavenPublishing {
    val jar = JavadocJar.Dokka("dokkaHtml")
    val platform = KotlinJvm(javadocJar = jar, sourcesJar = true)
    configure(platform)
}
