import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    implementation(libs.woody230.gradle.android)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies core library desugaring of Java APIs for an Android library.")

    plugin {
        displayName.set("Android Core Library Desugaring Plugin")
        className.set("com.bselzer.gradle.internal.android.desugar.plugin.AndroidDesugarPlugin")
    }
}