package io.github.woody230.gradle.convention

import org.gradle.configurationcache.extensions.capitalized

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("com.vanniktech.maven.publish.base")
}

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
}
