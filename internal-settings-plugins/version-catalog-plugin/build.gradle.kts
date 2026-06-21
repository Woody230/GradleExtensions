dependencies {
    implementation(libs.woody230.gradle.function)
    implementation(libs.woody230.gradle.internal.build.configuration)
}

pluginPublishExtension {
    description.set("Adds the version catalog to the dependency resolution management.")

    plugin {
        name.set("version-catalog")
        displayName.set("Version Catalog Plugin")
        description.set("Adds the standard libs version catalog to the dependency resolution management.")
        className.set("com.bselzer.gradle.internal.version.catalog.plugin.VersionCatalogPlugin")
    }

    plugin {
        name.set("internal-version-catalog")
        displayName.set("Internal Version Catalog Plugin")
        description.set("Adds the ioGithubWoody230GradleInternalLibs version catalog to the dependency resolution management.")
        className.set("com.bselzer.gradle.internal.version.catalog.plugin.InternalVersionCatalogPlugin")
    }
}