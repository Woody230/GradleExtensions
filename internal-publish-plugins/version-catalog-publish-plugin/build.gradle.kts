plugins {
    id(libs.plugins.woody230.gradle.convention.plugin.get().pluginId)
}

dependencies {
    api(projects.mavenPublishPlugin)
    implementation(libs.woody230.gradle.internal.named.version.catalog)
}

val pluginDescription = "Internal publishing for version catalogs."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.internal.version.catalog.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Internal Version Catalog Publishing Plugin"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.internal.version.catalog.publish.plugin.VersionCatalogPublishPlugin"
            tags.add("internal")
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}