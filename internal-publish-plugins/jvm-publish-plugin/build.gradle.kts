plugins {
    `kotlin-dsl`
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(libs.dokka.gradle.plugin)
    implementation(projects.mavenPublishPlugin)
}

val pluginDescription = "Internal publishing for Kotlin JVM projects."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.jvm.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Kotlin JVM Project Publishing"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.jvm.publish.plugin.JvmPublishPlugin"
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}