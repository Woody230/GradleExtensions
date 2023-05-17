import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

plugins {
    `kotlin-dsl`
}

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)
}

pluginPublishExtension {
    description.set("Applies core library desugaring of Java APIs for Android.")

    plugin {
        name.set("android.desugar")
        displayName.set("Android Core Library Desugaring Plugin")
        className.set("com.bselzer.gradle.android.desugar.plugin.AndroidDesugarPlugin")
    }
}