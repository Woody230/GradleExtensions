dependencies {
    api(libs.woody230.gradle.function)
}

pluginPublishExtension {
    description.set("Manages properties for a composite build.")

    plugin {
        className.set("com.bselzer.gradle.internal.composite.property.plugin.CompositePropertyPlugin")
    }
}