package com.bselzer.gradle.kotlin.multiplatform.publish.plugin

import com.bselzer.gradle.maven.publish.plugin.MavenPublishPlugin
import com.bselzer.gradle.maven.publish.plugin.MavenPublishPluginExtension
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project

class MultiplatformPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: MavenPublishPluginExtension
        get() = multiplatformPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() {
            val jar = JavadocJar.Dokka("dokkaHtml")
            return KotlinMultiplatform(javadocJar = jar)
        }
}
