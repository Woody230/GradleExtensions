import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.compose.plugin)
    implementation(libs.woody230.gradle.android)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform plugin.")

    plugin {
        displayName.set("Compose Multiplatform Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.compose.plugin.MultiplatformComposePlugin")
    }
}