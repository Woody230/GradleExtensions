package io.github.woody230.gradle.convention

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
    coordinates(groupId = group.toString(), artifactId = name, version = version.toString())
    pom {
        name.set("gradle-${project.name}")

        developers {
            developer {
                id.set("Woody230")
                name.set("Brandon Selzer")
                email.set("bselzer1@outlook.com")
            }
        }

        url.set(repo)
        scm { url.set(repo) }
    }
}
