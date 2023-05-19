import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.android.gradle.plugin)
    api(libs.compose.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform plugin.")

    plugin {
        name.set("multiplatform-compose")
        displayName.set("Compose Multiplatform Plugin")
        className.set("com.bselzer.gradle.multiplatform.compose.plugin.MultiplatformComposePlugin")
    }
}