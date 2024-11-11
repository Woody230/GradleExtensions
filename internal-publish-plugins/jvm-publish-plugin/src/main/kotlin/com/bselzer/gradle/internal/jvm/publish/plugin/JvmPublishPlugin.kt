package com.bselzer.gradle.internal.jvm.publish.plugin

import com.bselzer.gradle.function.properties.*
import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishPlugin
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project

class JvmPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: JvmPublishExtension
        get() = jvmPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() {
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

            return KotlinJvm(
                javadocJar = jar,
                sourcesJar = sourcesEnabled
            )
        }

    override fun apply(project: Project) = with(project) {
        setupGradleProperties()

        // TODO libs.plugins.dokka.get().pluginId
        pluginManager.apply("org.jetbrains.dokka")

        super.apply(project)
    }

    private fun Project.setupGradleProperties() {
        injectLocalProperty(LocalProperty.JAVADOC_ENABLED, GradleProperty.JAVADOC_ENABLED)
        injectLocalProperty(LocalProperty.SOURCES_ENABLED, GradleProperty.SOURCES_ENABLED)
    }
}