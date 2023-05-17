import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

plugins {
    `kotlin-dsl`
}

dependencies {
    api(libs.moko.resources.gradle.plugin)
    implementation(projects.androidPlugin)
    implementation(projects.multiplatformPublishPlugin)
}

pluginPublishExtension {
    description.set("Applies the Moko Resources plugin for Kotlin Multiplatform.")

    plugin {
        name.set("multiplatform-resource")
        displayName.set("Kotlin Multiplatform Moko Resources Plugin")
        className.set("com.bselzer.gradle.kotlin.multiplatform.resource.plugin.MultiplatformMokoResourcesPlugin")
    }
}