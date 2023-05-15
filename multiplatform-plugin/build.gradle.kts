plugins {
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
    `kotlin-dsl`
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(projects.multiplatform)
}