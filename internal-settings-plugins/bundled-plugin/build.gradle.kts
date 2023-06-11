dependencies {
    api(projects.compositePropertyPlugin)
    api(projects.versionCatalogPlugin)
    api(libs.toolchain.plugin)
}

pluginPublishExtension {
    description.set("Internal settings plugin bundling the other settings plugins together.")

    plugin {
        className.set("com.bselzer.gradle.internal.bundled.plugin.BundledPlugin")
    }
}