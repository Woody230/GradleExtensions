dependencies {
    api(gradleApi())
    api(libs.buildkonfig.plugin)
    api(libs.buildkonfig.compiler)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Applies the BuildKonfig plugin.")

    plugin {
        displayName.set("BuildKonfig Plugin")
        className.set("com.bselzer.gradle.internal.buildkonfig.plugin.BuildKonfigPlugin")
    }
}
