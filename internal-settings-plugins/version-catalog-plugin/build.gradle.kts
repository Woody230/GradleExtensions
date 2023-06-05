dependencies {
    implementation(libs.woody230.gradle.function)
}

pluginPublishExtension {
    description.set("Adds the version catalog to the dependency resolution management.")

    plugin {
        className.set("com.bselzer.gradle.internal.version.catalog.plugin.VersionCatalogPlugin")
    }
}