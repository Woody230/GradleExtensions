import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.multiplatform)
}

pluginPublishExtension {
    description.set("Applies the Kotlin Multiplatform plugin.")

    plugin {
        name.set("multiplatform")
        displayName.set("Kotlin Multiplatform Plugin")
        className.set("com.bselzer.gradle.multiplatform.plugin.MultiplatformPlugin")
    }
}