package com.bselzer.gradle.kotlin.multiplatform.publish.plugin

import com.bselzer.gradle.maven.publish.plugin.MavenPublishPlugin
import com.bselzer.gradle.maven.publish.plugin.MavenPublishPluginExtension
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaPlugin

class MultiplatformPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: MavenPublishPluginExtension
        get() = multiplatformPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() {
            val jar = JavadocJar.Dokka("dokkaHtml")
            return KotlinMultiplatform(javadocJar = jar)
        }

    override fun apply(project: Project) = with(project) {
        plugins.apply(DokkaPlugin::class.java)
        super.apply(project)
    }
}
