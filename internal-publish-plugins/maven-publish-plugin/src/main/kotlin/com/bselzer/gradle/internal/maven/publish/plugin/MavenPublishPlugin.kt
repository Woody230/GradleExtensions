package com.bselzer.gradle.internal.maven.publish.plugin

import com.bselzer.gradle.function.properties.*
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.Platform
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPomLicenseSpec
import org.gradle.kotlin.dsl.getByType

abstract class MavenPublishPlugin : Plugin<Project> {
    protected abstract val Project.mavenPublishExtension: MavenPublishExtension

    protected abstract val Project.mavenPublishPlatform: Platform

    override fun apply(project: Project): Unit = with(project) {
        setupGradleProperties()

        val extension = mavenPublishExtension.apply {
            coordinates.group.convention("io.github.woody230")
            coordinates.module.convention(name)
            developers.convention { }
            licensing.convention(Licensing.NONE)
        }

        afterEvaluate {
            with(extensions.getByType<MavenPublishBaseExtension>()) {
                configureCoordinates(extension)
                configurePom(extension)
                configure(project.mavenPublishPlatform)

                publishToMavenCentral(
                    host = SonatypeHost.S01,
                    automaticRelease = false
                )

                if (getBooleanPropertyOrFalse(GradleProperty.SIGNING_ENABLED)) {
                    project.logger.lifecycle("Publishing with signing enabled.")
                    signAllPublications()
                }
                else {
                    project.logger.lifecycle("Publishing with signing disabled.")
                }
            }
        }

        // TODO libs.plugins.vanniktech.publish.get().pluginId
        pluginManager.apply("com.vanniktech.maven.publish.base")
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
            name = components.joinToString(separator = " ") { component ->
                component.replaceFirstChar(Char::uppercase)
            },
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

    private fun Project.setupGradleProperties() {
        injectLocalProperty(LocalProperty.SIGNING_ENABLED, GradleProperty.SIGNING_ENABLED)
        injectLocalProperty(LocalProperty.SONATYPE_USERNAME, GradleProperty.MAVEN_CENTRAL_USERNAME)
        injectLocalProperty(LocalProperty.SONATYPE_PASSWORD, GradleProperty.MAVEN_CENTRAL_PASSWORD)
        injectLocalProperty(LocalProperty.SIGNING_KEY_ID, GradleProperty.SIGNING_KEY_ID)
        injectLocalProperty(LocalProperty.SIGNING_PASSWORD, GradleProperty.SIGNING_PASSWORD)
        injectLocalProperty(LocalProperty.SIGNING_KEY, GradleProperty.SIGNING_KEY) { keyPath ->
            project.file(keyPath).readText()
        }
    }
}