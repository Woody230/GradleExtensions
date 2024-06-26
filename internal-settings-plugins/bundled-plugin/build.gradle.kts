dependencies {
    api(projects.compositeBuildPlugin)
    api(projects.compositePropertyPlugin)
    api(projects.compositePublishPlugin)
    api(projects.compositeTestPlugin)
    api(projects.versionCatalogPlugin)
    api(libs.toolchain.plugin)
}

pluginPublishExtension {
    description.set("Internal settings plugin bundling the other settings plugins together.")

    plugin {
        className.set("com.bselzer.gradle.internal.bundled.plugin.BundledPlugin")
    }
}