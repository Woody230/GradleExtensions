import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

plugins {
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    subGroupId.set("gradle")
    version.set(rootProject.version.toString())
    repository.set("https://github.com/Woody230/GradleExtensions")
    description.set("Applies core library desugaring of Java APIs for Android.")

    plugin {
        name.set("android.desugar")
        displayName.set("Android Core Library Desugaring")
        className.set("com.bselzer.gradle.android.desugar.plugin.AndroidDesugarPlugin")
    }
}

apply(plugin = libs.plugins.woody230.gradle.plugin.publish.get().pluginId)