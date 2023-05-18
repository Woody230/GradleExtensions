import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

pluginPublishExtension {
    description.set("Applies core library desugaring of Java APIs for Android.")

    plugin {
        name.set("android.desugar")
        displayName.set("Android Core Library Desugaring Plugin")
        className.set("com.bselzer.gradle.android.desugar.plugin.AndroidDesugarPlugin")
    }
}