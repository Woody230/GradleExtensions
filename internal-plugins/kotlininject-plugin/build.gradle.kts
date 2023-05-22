import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.ksp.plugin)
    implementation(libs.woody230.gradle.multiplatform)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies the KSP plugin for KotlinInject.")

    plugin {
        displayName.set("KotlinInject Plugin")
        className.set("com.bselzer.gradle.internal.kotlininject.plugin.KotlinInjectPlugin")
    }
}