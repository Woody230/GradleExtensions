plugins {
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
}

dependencies {
    api(projects.api)
    api(libs.moko.resources.gradle.plugin)
    implementation(projects.androidPlugin)
    implementation(projects.multiplatformPublishPlugin)
}