package com.bselzer.gradle.internal.maven.publish.plugin

import com.bselzer.gradle.function.properties.containsKeys
import com.bselzer.gradle.function.properties.localProperties
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishBasePlugin
import com.vanniktech.maven.publish.Platform
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPomLicenseSpec
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.getByType

abstract class MavenPublishPlugin : Plugin<Project> {
    protected abstract val Project.mavenPublishExtension: MavenPublishExtension

    protected abstract val Project.mavenPublishPlatform: Platform

    override fun apply(project: Project) = with(project) {
        plugins.apply(MavenPublishBasePlugin::class.java)

        val extension = mavenPublishExtension.apply {
            coordinates.group.convention("io.github.woody230")
            coordinates.module.convention(name)
            developers.convention { }
            licensing.convention(Licensing.NONE)
        }

        setupGradleProperties()

        with(extensions.getByType<MavenPublishBaseExtension>()) {
            configureCoordinates(extension)
            configurePom(extension)
            configure(project.mavenPublishPlatform)

            publishToMavenCentral(
                host = SonatypeHost.S01,
                automaticRelease = false
            )

            if (hasProperty(GradleProperty.SIGNING_KEY) && hasProperty(GradleProperty.SIGNING_PASSWORD)) {
                signAllPublications()
            }
        }
    }

    private fun MavenPublishBaseExtension.configureCoordinates(extension: MavenPublishExtension) {
        val groupId = "${extension.coordinates.group.get()}.${extension.coordinates.category.get()}"
        val artifactId = extension.coordinates.module.get()
        val version = extension.version.get()
        coordinates(groupId, artifactId, version)
    }

    private fun MavenPom.configureLicenses(type: Licensing) = licenses {
        when (type) {
            Licensing.NONE -> {}
            Licensing.APACHE_2_0 -> configureApache2()
        }
    }

    private fun MavenPomLicenseSpec.configureApache2() = license {
        name.set("The Apache Software License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("repo")
    }

    private fun MavenPom.configureDevelopers(configure: MavenPomDeveloperSpec.() -> Unit) = developers {
        configure()

        developer {
            id.set("Woody230")
            name.set("Brandon Selzer")
            email.set("bselzer1@outlook.com")
        }
    }

    private fun MavenPom.configureScm(repo: String) {
        url.set(repo)
        scm { url.set(repo) }
    }

    private fun MavenPublishBaseExtension.configurePom(extension: MavenPublishExtension) = pom {
        val components = extension.coordinates.category.get().split(".") + extension.coordinates.module.get().split("-")
        configure(
            name = components.joinToString(separator = " ", transform = String::capitalized),
            description = extension.description.get(),
            licensing = extension.licensing.get(),
            devs = extension.developers.get(),
            repo = extension.repository.get()
        )
    }

    private fun MavenPom.configure(
        name: String,
        description: String,
        licensing: Licensing,
        devs: MavenPomDeveloperSpec.() -> Unit = {},
        repo: String
    ) {
        this.name.set(name)
        this.description.set(description)
        configureLicenses(licensing)
        configureDevelopers(devs)
        configureScm(repo)
    }

    private fun Project.setupGradleProperties() = with(properties) {
        val localProperties = localProperties

        if (localProperties.containsKeys(LocalProperty.SONATYPE_USERNAME, LocalProperty.SONATYPE_PASSWORD)) {
            setProperty(GradleProperty.MAVEN_CENTRAL_USERNAME, localProperties.getProperty(LocalProperty.SONATYPE_USERNAME))
            setProperty(GradleProperty.MAVEN_CENTRAL_PASSWORD, localProperties.getProperty(LocalProperty.SONATYPE_PASSWORD))
        }

        if (localProperties.containsKeys(LocalProperty.SIGNING_KEY_ID, LocalProperty.SIGNING_KEY, LocalProperty.SIGNING_PASSWORD)) {
            setProperty(GradleProperty.SIGNING_KEY_ID, localProperties.getProperty(LocalProperty.SIGNING_KEY_ID))
            setProperty(GradleProperty.SIGNING_PASSWORD, localProperties.getProperty(LocalProperty.SIGNING_PASSWORD))

            val keyPath = localProperties.getProperty(LocalProperty.SIGNING_KEY)
            setProperty(GradleProperty.SIGNING_KEY, project.file(keyPath).readText())
        }
    }
}