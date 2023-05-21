import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.moko.resources.plugin)
    implementation(libs.android.plugin)
    implementation(projects.multiplatformPublishPlugin)
}

pluginPublishExtension {
    description.set("Applies the Moko Resources plugin for Kotlin Multiplatform.")

    plugin {
        displayName.set("Kotlin Multiplatform Moko Resources Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.resource.plugin.MultiplatformResourcesPlugin")
    }
}