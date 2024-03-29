package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.configurationcache.extensions.capitalized

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("com.vanniktech.maven.publish.base")
}

setupGradleProperties()

mavenPublishing {
    val category = when {
        rootDir.name.contains("internal") -> "gradle.internal"
        else -> "gradle"
    }

    val group = "io.github.woody230.$category"
    val module = name
    val version = libs.versions.woody230.gradle.get()
    coordinates(groupId = group, artifactId = module, version = version)

    pom {
        val components = category.split(".") + module.split("-")
        name.set(components.joinToString(separator = " ", transform = String::capitalized))

        developers {
            developer {
                id.set("Woody230")
                name.set("Brandon Selzer")
                email.set("bselzer1@outlook.com")
            }
        }

        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }

        val repo = "https://github.com/Woody230/GradleExtensions"
        url.set(repo)
        scm { url.set(repo) }
    }

    publishToMavenCentral(
        host = SonatypeHost.S01,
        automaticRelease = false
    )

    if (hasProperty(GradleProperty.SIGNING_KEY) && hasProperty(GradleProperty.SIGNING_PASSWORD)) {
        signAllPublications()
    }
}

fun Project.setupGradleProperties() {
    val localProperties = compositeLocalProperties

    if (localProperties.containsKeys(LocalProperty.SONATYPE_USERNAME, LocalProperty.SONATYPE_PASSWORD)) {
        addOrReplaceProperty(GradleProperty.MAVEN_CENTRAL_USERNAME, localProperties.getProperty(LocalProperty.SONATYPE_USERNAME))
        addOrReplaceProperty(GradleProperty.MAVEN_CENTRAL_PASSWORD, localProperties.getProperty(LocalProperty.SONATYPE_PASSWORD))
    }

    if (localProperties.containsKeys(LocalProperty.SIGNING_KEY_ID, LocalProperty.SIGNING_KEY, LocalProperty.SIGNING_PASSWORD)) {
        addOrReplaceProperty(GradleProperty.SIGNING_KEY_ID, localProperties.getProperty(LocalProperty.SIGNING_KEY_ID))
        addOrReplaceProperty(GradleProperty.SIGNING_PASSWORD, localProperties.getProperty(LocalProperty.SIGNING_PASSWORD))

        val keyPath = localProperties.getProperty(LocalProperty.SIGNING_KEY)
        addOrReplaceProperty(GradleProperty.SIGNING_KEY, project.file(keyPath).readText())
    }
}