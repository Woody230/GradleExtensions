import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.moko.resources.plugin)
    implementation(libs.woody230.gradle.android)
    implementation(projects.multiplatformPublishPlugin)
}

pluginPublishExtension {
    description.set("Applies the Moko Resources plugin.")

    plugin {
        className.set("com.bselzer.gradle.internal.moko.resources.plugin.MokoResourcesPlugin")
    }
}