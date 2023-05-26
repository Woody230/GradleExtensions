package com.bselzer.gradle.internal.jvm.publish.plugin

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
            val jar = JavadocJar.Dokka("dokkaHtml")
            return KotlinJvm(javadocJar = jar, sourcesJar = true)
        }

    override fun apply(project: Project) = with(project) {
        // TODO libs.plugins.dokka.get().pluginId
        plugins.apply("org.jetbrains.dokka")
        super.apply(project)
    }
}