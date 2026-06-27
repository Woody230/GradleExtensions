dependencies {
    api(libs.compose.plugin)
    api(libs.compose.compiler.plugin)
    implementation(libs.woody230.gradle.android)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform plugin.")

    plugin {
        displayName.set("Compose Multiplatform Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.compose.plugin.MultiplatformComposePlugin")
    }
}
