package io.github.woody230.gradle.convention

import com.vanniktech.maven.publish.GradlePublishPlugin
import libs

plugins {
    alias(libs.plugins.java)
    alias(libs.plugins.gradle.publish)

    // TODO https://github.com/radoslaw-panuszewski/typesafe-conventions-gradle-plugin/issues/82
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
