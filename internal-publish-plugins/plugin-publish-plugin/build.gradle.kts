plugins {
    `kotlin-dsl`
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    implementation(projects.mavenPublishPlugin)
}

val pluginDescription = "Internal publishing for gradle plugins."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.plugin.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Gradle Plugin Publishing"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.plugin.publish.plugin.PluginPublishPlugin"
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}