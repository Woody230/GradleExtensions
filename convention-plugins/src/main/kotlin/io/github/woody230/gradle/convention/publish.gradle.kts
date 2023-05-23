package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.GradlePublishPlugin
import org.gradle.configurationcache.extensions.capitalized

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("org.gradle.java-gradle-plugin")
    id("com.gradle.plugin-publish")
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish.base")
}

val repo = "https://github.com/Woody230/GradleExtensions"
gradlePlugin {
    website.set(repo)
    vcsUrl.set("$repo.git")
}

mavenPublishing {
    configure(GradlePublishPlugin())

    val group = "io.github.woody230.gradle.internal"
    val version = libs.versions.woody230.gradle.get()
    coordinates(groupId = group, artifactId = name, version = version)

    pom {
        val module = project.name.split("-").joinToString(separator = " ", transform = String::capitalized)
        name.set("Gradle Internal $module")

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

        url.set(repo)
        scm { url.set(repo) }
    }
}
