import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.properties)
    api(libs.kotlin.gradle.plugin)
    api(libs.dokka.gradle.plugin)
    implementation(libs.woody230.gradle.maven.publish)
}

pluginPublishExtension {
    description.set("Internal publishing for Kotlin Multiplatform projects.")

    plugin {
        name.set("multiplatform-publish")
        displayName.set("Kotlin Multiplatform Project Publish Plugin")
        className.set("com.bselzer.gradle.multiplatform.publish.plugin.MultiplatformPublishPlugin")
    }
}