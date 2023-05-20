import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.android.plugin)
}

pluginPublishExtension {
    description.set("Applies the Android library plugin.")

    plugin {
        className.set("com.bselzer.gradle.android.library.plugin.AndroidLibraryPlugin")
    }
}