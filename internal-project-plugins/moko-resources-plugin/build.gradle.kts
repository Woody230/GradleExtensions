dependencies {
    api(libs.moko.resources.plugin)
    implementation(libs.woody230.gradle.android)
    implementation(libs.woody230.gradle.multiplatform)
    implementation(projects.multiplatformPublishPlugin)
}

pluginPublishExtension {
    description.set("Applies the Moko Resources plugin.")

    plugin {
        className.set("com.bselzer.gradle.internal.moko.resources.plugin.MokoResourcesPlugin")
    }
}