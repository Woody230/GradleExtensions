plugins {
    `kotlin-dsl`
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    implementation(projects.mavenPublishPlugin)
    implementation(libs.toolchain.gradle.plugin)
}

val pluginDescription = "Internal gradle settings configuring."
gradlePlugin {
    plugins {
        val id = libs.plugins.woody230.gradle.settings.get().pluginId
        create(id) {
            this.id = id
            displayName = "Settings Plugin"
            description = pluginDescription
            implementationClass = "com.bselzer.gradle.settings.plugin.SettingsPlugin"
        }
    }
}

mavenPublishing {
    pom {
        description.set(pluginDescription)
    }
}