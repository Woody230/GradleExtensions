dependencies {
    api(libs.woody230.gradle.multiplatform)
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform test dependencies for the Kotlin Multiplatform source sets.")

    plugin {
        displayName.set("Compose Multiplatform Test Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.compose.test.plugin.MultiplatformComposeTestPlugin")
    }
}