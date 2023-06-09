dependencies {
    api(gradleApi())
    api(libs.aboutlibraries.plugin)
    implementation(libs.moko.resources.plugin)
    implementation(libs.woody230.gradle.multiplatform)
}

pluginPublishExtension {
    description.set("Applies the AboutLibraries plugin.")

    plugin {
        displayName.set("AboutLibraries Plugin")
        className.set("com.bselzer.gradle.internal.aboutlibraries.plugin.AboutLibrariesPlugin")
    }
}