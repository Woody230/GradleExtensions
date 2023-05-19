import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)
}

pluginPublishExtension {
    description.set("Applies the Android library plugin.")

    plugin {
        name.set("android.library")
        displayName.set("Android Library Plugin")
        className.set("com.bselzer.gradle.android.library.plugin.AndroidLibraryPlugin")
    }
}