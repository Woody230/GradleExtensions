plugins {
    id(libs.plugins.woody230.gradle.convention.plugin.get().pluginId)
}

dependencies {
    api(libs.dokka.plugin)
    api(projects.mavenPublishPlugin)
}

val pluginDescription = "Internal publishing for Kotlin JVM projects."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.internal.jvm.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Internal Kotlin JVM Project Publishing Plugin"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.internal.jvm.publish.plugin.JvmPublishPlugin"
            tags.add("internal")
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}