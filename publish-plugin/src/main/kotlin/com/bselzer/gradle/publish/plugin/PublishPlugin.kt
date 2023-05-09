package com.bselzer.gradle.publish.plugin

import com.bselzer.gradle.api.containsKeys
import com.bselzer.gradle.api.localProperties
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishBasePlugin
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.kotlin.gradle.plugin.extraProperties

class PublishPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(DokkaPlugin::class.java)
        plugins.apply(MavenPublishBasePlugin::class.java)

        val extension = extensions.create("publishExtension", PublishPluginExtension::class.java)
        extension.groupId.convention("io.github.woody230")
        extension.artifactId.convention(name)
        extension.devs.convention { }

        setupGradleProperties()

        project.afterEvaluate {
            with(extensions.getByType(MavenPublishBaseExtension::class.java)) {
                configureCoordinates(extension)
                configurePom(project, extension)
                configureMultiplatform()

                publishToMavenCentral(
                    host = SonatypeHost.S01,
                    automaticRelease = false
                )

                if (localProperties.containsKeys(LocalProperty.SIGNING_KEY, LocalProperty.SIGNING_PASSWORD)) {
                    signAllPublications()
                }
            }
        }
    }

    private fun MavenPublishBaseExtension.configureCoordinates(extension: PublishPluginExtension) {
        val groupId = "${extension.groupId.get()}.${extension.subGroupId.get()}"
        val artifactId = extension.artifactId.get()
        val version = extension.version.get()
        coordinates(groupId, artifactId, version)
    }

    private fun MavenPublishBaseExtension.configureMultiplatform() {
        val jar = JavadocJar.Dokka("dokkaHtml")
        val platform = KotlinMultiplatform(javadocJar = jar)
        configure(platform)
    }

    private fun MavenPom.configureLicenses() = licenses { licenses ->
        licenses.license { license ->
            license.name.set("The Apache Software License, Version 2.0")
            license.url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            license.distribution.set("repo")
        }
    }

    private fun MavenPom.configureDevelopers(configure: MavenPomDeveloperSpec.() -> Unit) = developers { developers ->
        configure(developers)

        developers.developer { developer ->
            developer.id.set("Woody230")
            developer.name.set("Brandon Selzer")
            developer.email.set("bselzer1@outlook.com")
        }
    }

    private fun MavenPom.configureScm(repo: String) {
        url.set(repo)
        scm { url.set(repo) }
    }

    private fun MavenPublishBaseExtension.configurePom(project: Project, extension: PublishPluginExtension) = pom { pom ->
        pom.configure(
            name = "${extension.subGroupId.get()}-${project.name}",
            description = extension.description.get(),
            devs = extension.devs.get(),
            repo = extension.repository.get()
        )
    }

    private fun MavenPom.configure(
        name: String,
        description: String,
        devs: MavenPomDeveloperSpec.() -> Unit = {},
        repo: String
    ) {
        this.name.set(name)
        this.description.set(description)
        configureLicenses()
        configureDevelopers(devs)
        configureScm(repo)
    }

    private fun Project.setupGradleProperties() = with(extraProperties) {
        val localProperties = localProperties

        set(ExtraProperty.MAVEN_CENTRAL_USERNAME, localProperties.getProperty(LocalProperty.SONATYPE_USERNAME))
        set(ExtraProperty.MAVEN_CENTRAL_PASSWORD, localProperties.getProperty(LocalProperty.SONATYPE_PASSWORD))

        if (localProperties.containsKeys(LocalProperty.SIGNING_KEY, LocalProperty.SIGNING_PASSWORD)) {
            set(ExtraProperty.SIGNING_KEY_ID, localProperties.getProperty(LocalProperty.SIGNING_KEY_ID))
            set(ExtraProperty.SIGNING_PASSWORD, localProperties.getProperty(LocalProperty.SIGNING_PASSWORD))

            val keyPath = localProperties.getProperty(LocalProperty.SIGNING_KEY)
            set(ExtraProperty.SIGNING_KEY, project.file(keyPath).readText())
        }
    }
}
