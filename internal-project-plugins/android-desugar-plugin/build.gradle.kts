dependencies {
    api(gradleApi())
    implementation(libs.woody230.gradle.android)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Applies core library desugaring of Java APIs for Android.")

    plugin {
        displayName.set("Android Core Library Desugaring Plugin")
        className.set("com.bselzer.gradle.internal.android.desugar.plugin.AndroidDesugarPlugin")
    }
}
