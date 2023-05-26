package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.GradlePublishPlugin

// TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
plugins {
    id("org.gradle.java-gradle-plugin")
    id("com.gradle.plugin-publish")
    id("io.github.woody230.gradle.convention.publish")
}

val repo = "https://github.com/Woody230/GradleExtensions"
gradlePlugin {
    website.set(repo)
    vcsUrl.set("$repo.git")
}

mavenPublishing {
    configure(GradlePublishPlugin())
}
