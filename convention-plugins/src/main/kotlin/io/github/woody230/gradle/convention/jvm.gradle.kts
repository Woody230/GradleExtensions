package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlin.jvm")
    id("io.github.woody230.gradle.convention.publish")
}

setupGradleProperties()

mavenPublishing {
    val jar: JavadocJar
    if (getBooleanPropertyOrFalse(GradleProperty.JAVADOC_ENABLED)) {
        logger.lifecycle("Publishing with javadoc using dokka.")
        jar = JavadocJar.Dokka("dokkaHtml")
    }
    else {
        logger.lifecycle("Publishing without javadoc.")
        jar = JavadocJar.None()
    }

    val sourcesEnabled = getBooleanPropertyOrFalse(GradleProperty.SOURCES_ENABLED)
    logger.lifecycle("Publishing with sources ${if (sourcesEnabled) "enabled" else "disabled"}.")

    val platform = KotlinJvm(javadocJar = jar, sourcesJar = sourcesEnabled)
    configure(platform)
}

private fun Project.setupGradleProperties() {
    injectLocalProperty(LocalProperty.JAVADOC_ENABLED, GradleProperty.JAVADOC_ENABLED)
    injectLocalProperty(LocalProperty.SOURCES_ENABLED, GradleProperty.SOURCES_ENABLED)
}
