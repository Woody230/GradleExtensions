dependencies {
    implementation(projects.mavenPublishPlugin)
}

val pluginDescription = "Internal publishing for gradle plugins."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.internal.plugin.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Internal Gradle Plugin Publishing Plugin"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.internal.plugin.publish.plugin.PluginPublishPlugin"
            tags.add("internal")
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}