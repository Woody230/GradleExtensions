dependencies {
    implementation(libs.woody230.gradle.function)
    api(libs.woody230.gradle.internal.composite.task)
}

pluginPublishExtension {
    description.set("Adds tasks for building and cleaning all of the projects included in the composite build.")

    plugin {
        className.set("com.bselzer.gradle.internal.composite.project.plugin.CompositeBuildPlugin")
    }
}