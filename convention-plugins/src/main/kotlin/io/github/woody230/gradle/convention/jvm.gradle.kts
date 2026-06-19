package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.SourcesJar
import libs

plugins {
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.jvm)

    // TODO https://github.com/radoslaw-panuszewski/typesafe-conventions-gradle-plugin/issues/82
    id("io.github.woody230.gradle.convention.publish")
}

setupGradleProperties()

mavenPublishing {
    val jar: JavadocJar
    if (getBooleanPropertyOrFalse(GradleProperty.JAVADOC_ENABLED)) {
        logger.lifecycle("Publishing with javadoc using dokka.")
        jar = JavadocJar.Dokka("dokkaGenerateHtml")
    }
    else {
        logger.lifecycle("Publishing without javadoc.")
        jar = JavadocJar.None()
    }

    val sourcesEnabled = getBooleanPropertyOrFalse(GradleProperty.SOURCES_ENABLED)
    logger.lifecycle("Publishing with sources ${if (sourcesEnabled) "enabled" else "disabled"}.")

    val platform = KotlinJvm(
        javadocJar = jar,
        sourcesJar = if (sourcesEnabled) SourcesJar.Sources() else SourcesJar.Empty()
    )
    configure(platform)
}

private fun Project.setupGradleProperties() {
    injectLocalProperty(LocalProperty.JAVADOC_ENABLED, GradleProperty.JAVADOC_ENABLED)
    injectLocalProperty(LocalProperty.SOURCES_ENABLED, GradleProperty.SOURCES_ENABLED)
}
