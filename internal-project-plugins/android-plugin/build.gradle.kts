dependencies {
    api(gradleApi())
    api(libs.android.plugin)
    api(libs.kotlin.plugin)
    implementation(libs.woody230.gradle.android)
    implementation(libs.woody230.gradle.function)
    implementation(libs.woody230.gradle.internal.models)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Applies the Android gradle plugin.")

    plugin {
        name.set("android-application")
        displayName.set("Android Application Gradle Plugin")
        description.set("Applies the Android application gradle plugin.")
        className.set("com.bselzer.gradle.internal.android.application.plugin.AndroidApplicationPlugin")
    }

    plugin {
        name.set("multiplatform-android-library")
        displayName.set("Multiplatform Android Library Gradle Plugin")
        description.set("Applies the multiplatform Android library gradle plugin.")
        className.set("com.bselzer.gradle.internal.android.kotlin.multiplatform.library.plugin.MultiplatformAndroidLibraryPlugin")
    }

    plugin {
        name.set("android-library")
        displayName.set("Android Library Gradle Plugin")
        description.set("Applies the Android library gradle plugin.")
        className.set("com.bselzer.gradle.internal.android.library.plugin.AndroidLibraryPlugin")
    }
}
