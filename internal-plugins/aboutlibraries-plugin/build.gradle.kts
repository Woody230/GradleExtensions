import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.aboutlibraries.plugin)
    implementation(libs.moko.resources.plugin)
    implementation(libs.woody230.gradle.multiplatform)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the AboutLibraries plugin.")

    plugin {
        displayName.set("AboutLibraries Plugin")
        className.set("com.bselzer.gradle.internal.aboutlibraries.plugin.AboutLibrariesPlugin")
    }
}