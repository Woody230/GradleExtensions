plugins {
    alias(libs.plugins.dsl)
    id(libs.plugins.java.get().pluginId)
    alias(libs.plugins.gradle.publish)
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(libs.vanniktech.publish.gradle.plugin)
}

gradlePlugin {
    website.set("https://github.com/Woody230/KotlinExtensions")
    vcsUrl.set("https://github.com/Woody230/KotlinExtensions.git")

    plugins {
        create("publishPlugin") {
            id = "io.github.woody230.gradle.plugin.publish"
            displayName = "Plugin Publishing"
            description = "Internal publishing for gradle plugins."
            implementationClass = "com.bselzer.gradle.plugin.publish.plugin.PluginPublishPlugin"
        }
    }
}