import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

plugins {
    `kotlin-dsl`
}

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)
}

pluginPublishExtension {
    description.set("Applies the Android plugin.")

    plugin {
        name.set("android")
        displayName.set("Android Plugin")
        className.set("com.bselzer.gradle.android.plugin.AndroidPlugin")
    }
}