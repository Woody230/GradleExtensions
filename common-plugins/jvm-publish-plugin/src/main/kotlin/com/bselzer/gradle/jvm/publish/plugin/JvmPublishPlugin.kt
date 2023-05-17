package com.bselzer.gradle.jvm.publish.plugin

import com.bselzer.gradle.maven.publish.plugin.MavenPublishPlugin
import com.bselzer.gradle.maven.publish.plugin.MavenPublishPluginExtension
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project

class JvmPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: MavenPublishPluginExtension
        get() = jvmPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() {
            val jar = JavadocJar.Dokka("dokkaHtml")
            return KotlinJvm(javadocJar = jar, sourcesJar = true)
        }
}