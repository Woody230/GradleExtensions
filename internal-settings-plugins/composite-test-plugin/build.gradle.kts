dependencies {
    implementation(libs.woody230.gradle.function)
}

pluginPublishExtension {
    description.set("Adds tasks for testing all of the projects included in the composite build.")

    plugin {
        className.set("com.bselzer.gradle.internal.composite.test.plugin.CompositeTestPlugin")
    }
}