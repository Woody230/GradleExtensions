plugins {
    id(libs.plugins.java.get().pluginId)
    id(libs.plugins.gradle.publish.get().pluginId)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(libs.woody230.gradle.properties)
    api(libs.moko.resources.gradle.plugin)
    implementation(projects.androidPlugin)
    implementation(projects.multiplatformPublishPlugin)
}