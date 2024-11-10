package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.SonatypeHost

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
        name.set(components.joinToString(separator = " ") { component ->
            component.replaceFirstChar(Char::uppercase)
        })

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

    if (getBooleanPropertyOrFalse(GradleProperty.SIGNING_ENABLED)) {
        project.logger.lifecycle("Publishing with signing enabled.")
        signAllPublications()
    }
    else {
        project.logger.lifecycle("Publishing with signing disabled.")
    }
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