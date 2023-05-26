package com.bselzer.gradle.internal.multiplatform.publish.plugin

import com.bselzer.gradle.internal.maven.publish.plugin.MavenPublishPlugin
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.Platform
import org.gradle.api.Project

class MultiplatformPublishPlugin : MavenPublishPlugin() {
    override val Project.mavenPublishExtension: MultiplatformPublishExtension
        get() = multiplatformPublishExtension

    override val Project.mavenPublishPlatform: Platform
        get() {
            val jar = JavadocJar.Dokka("dokkaHtml")
            return KotlinMultiplatform(javadocJar = jar)
        }

    override fun apply(project: Project) = with(project) {
        // TODO libs.plugins.dokka.get().pluginId
        plugins.apply("org.jetbrains.dokka")
        super.apply(project)
    }
}
