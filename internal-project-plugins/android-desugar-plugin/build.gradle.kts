dependencies {
    api(gradleApi())
    implementation(libs.woody230.gradle.android)
}

pluginPublishExtension {
    description.set("Applies core library desugaring of Java APIs for Android.")

    plugin {
        displayName.set("Android Core Library Desugaring Plugin")
        className.set("com.bselzer.gradle.internal.android.desugar.plugin.AndroidDesugarPlugin")
    }
}