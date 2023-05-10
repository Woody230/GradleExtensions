package com.bselzer.gradle.kotlin.multiplatform.publish.plugin

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
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.extraProperties

class MultiplatformPublishPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply(MavenPublishBasePlugin::class.java)

        val extension = multiplatformPublishExtension {
            groupId.convention("io.github.woody230")
            artifactId.convention(name)
            devs.convention { }
        }

        setupGradleProperties()

        with(extensions.getByType<MavenPublishBaseExtension>()) {
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

    private fun MavenPublishBaseExtension.configureCoordinates(extension: MultiplatformPublishPluginExtension) {
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

    private fun MavenPom.configureLicenses() = licenses {
        license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            distribution.set("repo")
        }
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

    private fun MavenPublishBaseExtension.configurePom(project: Project, extension: MultiplatformPublishPluginExtension) = pom {
        configure(
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

        if (localProperties.containsKeys(LocalProperty.SONATYPE_USERNAME, LocalProperty.SONATYPE_PASSWORD)) {
            set(ExtraProperty.MAVEN_CENTRAL_USERNAME, localProperties.getProperty(LocalProperty.SONATYPE_USERNAME))
            set(ExtraProperty.MAVEN_CENTRAL_PASSWORD, localProperties.getProperty(LocalProperty.SONATYPE_PASSWORD))
        }

        if (localProperties.containsKeys(LocalProperty.SIGNING_KEY_ID, LocalProperty.SIGNING_KEY, LocalProperty.SIGNING_PASSWORD)) {
            set(ExtraProperty.SIGNING_KEY_ID, localProperties.getProperty(LocalProperty.SIGNING_KEY_ID))
            set(ExtraProperty.SIGNING_PASSWORD, localProperties.getProperty(LocalProperty.SIGNING_PASSWORD))

            val keyPath = localProperties.getProperty(LocalProperty.SIGNING_KEY)
            set(ExtraProperty.SIGNING_KEY, project.file(keyPath).readText())
        }
    }
}
