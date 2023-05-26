import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.ksp.plugin)
    implementation(libs.woody230.gradle.multiplatform)
}

pluginPublishExtension {
    description.set("Applies the KSP plugin for KotlinInject.")

    plugin {
        displayName.set("KotlinInject Plugin")
        className.set("com.bselzer.gradle.internal.kotlininject.plugin.KotlinInjectPlugin")
    }
}