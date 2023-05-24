import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.compose.plugin)
    implementation(libs.woody230.gradle.android)
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform plugin.")

    plugin {
        displayName.set("Compose Multiplatform Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.compose.plugin.MultiplatformComposePlugin")
    }
}