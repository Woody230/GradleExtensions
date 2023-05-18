plugins {
    `kotlin-dsl`
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

val pluginDescription = "Internal gradle project configuring."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.project.get().pluginId
        create(id) {
            this.id = id
            displayName = "Project Plugin"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.project.plugin.ProjectPlugin"
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}