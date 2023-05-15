plugins {
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
}

dependencies {
    api(projects.api)
    api(projects.multiplatform)
}