import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(libs.woody230.gradle.properties)
    api(libs.kotlin.plugin)
    api(libs.dokka.plugin)
    implementation(libs.woody230.gradle.internal.maven.publish.plugin)
}

pluginPublishExtension {
    description.set("Internal publishing for Kotlin Multiplatform projects.")

    plugin {
        displayName.set("Kotlin Multiplatform Project Publish Plugin")
        className.set("com.bselzer.gradle.internal.multiplatform.publish.plugin.MultiplatformPublishPlugin")
    }
}