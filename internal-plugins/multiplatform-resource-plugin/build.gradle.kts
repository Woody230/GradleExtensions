import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.moko.resources.plugin)
    implementation(libs.android.plugin)
    implementation(projects.multiplatformPublishPlugin)
}

pluginPublishExtension {
    description.set("Applies the Moko Resources plugin for Kotlin Multiplatform.")

    plugin {
        name.set("multiplatform-resource")
        displayName.set("Kotlin Multiplatform Moko Resources Plugin")
        className.set("com.bselzer.gradle.multiplatform.resource.plugin.MultiplatformResourcesPlugin")
    }
}