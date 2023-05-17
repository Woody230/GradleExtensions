import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

plugins {
    `kotlin-dsl`
}

dependencies {
    api(projects.androidPlugin)
    api(libs.compose.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the Compose Multiplatform plugin.")

    plugin {
        name.set("multiplatform-compose")
        displayName.set("Compose Multiplatform Plugin")
        className.set("com.bselzer.gradle.kotlin.multiplatform.compose.plugin.MultiplatformComposePlugin")
    }
}