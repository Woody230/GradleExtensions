dependencies {
    api(gradleApi())
    api(libs.aboutlibraries.plugin)
    implementation(libs.woody230.gradle.multiplatform)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Applies the AboutLibraries plugin.")

    plugin {
        displayName.set("AboutLibraries Plugin")
        className.set("com.bselzer.gradle.internal.aboutlibraries.plugin.AboutLibrariesPlugin")
    }
}
