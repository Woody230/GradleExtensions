import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.android.plugin)
}

pluginPublishExtension {
    description.set("Applies the Android gradle plugin.")

    plugin {
        name.set("android.library")
        displayName.set("Android Library Gradle Plugin")
        description.set("Applies the Android library gradle plugin.")
        className.set("com.bselzer.gradle.android.library.plugin.AndroidLibraryPlugin")
    }
}