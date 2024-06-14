import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.multiplatform)
    implementation(libs.woody230.gradle.function)
    implementation(libs.woody230.gradle.internal.models)
}

pluginPublishExtension {
    description.set("Applies the Kotlin Multiplatform plugin.")

    plugin {
        displayName.set("Kotlin Multiplatform Plugin")
        name.set("multiplatform")
        description.set("Applies the Kotlin Multiplatform plugin.")
        className.set("com.bselzer.gradle.internal.multiplatform.plugin.MultiplatformPlugin")
    }

    plugin {
        displayName.set("Kotlin Multiplatform Android Target Plugin")
        name.set("multiplatform-android-target")
        description.set("Applies the Android target for Kotlin Multiplatform.")
        className.set("com.bselzer.gradle.internal.multiplatform.android.target.plugin.MultiplatformAndroidTargetPlugin")
    }

    plugin {
        displayName.set("Kotlin Multiplatform JVM Target Plugin")
        name.set("multiplatform-jvm-target")
        description.set("Applies the JVM target for Kotlin Multiplatform.")
        className.set("com.bselzer.gradle.internal.multiplatform.jvm.target.plugin.MultiplatformJvmTargetPlugin")
    }
}