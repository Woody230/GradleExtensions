plugins {
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
    `kotlin-dsl`
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)
    implementation(projects.mavenPublishPlugin)
}