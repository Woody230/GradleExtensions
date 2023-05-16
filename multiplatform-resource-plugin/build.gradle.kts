plugins {
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(projects.api)
    api(libs.moko.resources.gradle.plugin)
    implementation(projects.androidPlugin)
    implementation(projects.multiplatformPublishPlugin)
}