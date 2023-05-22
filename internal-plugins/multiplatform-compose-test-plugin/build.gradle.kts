import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.multiplatform)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform test dependencies for the Kotlin Multiplatform source sets.")

    plugin {
        displayName.set("Compose Multiplatform Test Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.compose.test.plugin.MultiplatformComposeTestPlugin")
    }
}