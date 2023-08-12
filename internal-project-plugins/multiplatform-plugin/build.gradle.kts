import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.multiplatform)
    implementation(libs.woody230.gradle.function)
    implementation(libs.woody230.gradle.internal.models)
}

pluginPublishExtension {
    description.set("Applies the Kotlin Multiplatform plugin.")

    plugin {
        displayName.set("Kotlin Multiplatform Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.plugin.MultiplatformPlugin")
    }
}