plugins {
    `kotlin-dsl`
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(libs.kotlin.gradle.plugin)
    api(libs.vanniktech.publish.gradle.plugin)
}

val pluginDescription = "Internal publishing to Maven Central."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.maven.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Maven Publishing"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.maven.publish.plugin.MavenPublishPlugin"
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}