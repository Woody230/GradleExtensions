import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.multiplatform)
}

pluginPublishExtension {
    description.set("Applies the test dependencies for the Kotlin Multiplatform source sets.")

    plugin {
        displayName.set("Kotlin Multiplatform Test Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.test.plugin.MultiplatformTestPlugin")
    }
}