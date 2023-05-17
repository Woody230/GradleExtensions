plugins {
    id(libs.plugins.java.get().pluginId)
    id(libs.plugins.gradle.publish.get().pluginId)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(projects.api)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)
    api(libs.publish.gradle.plugin)
    implementation(projects.mavenPublishPlugin)
}

gradlePlugin {
    website.set("https://github.com/Woody230/GradleExtensions")
    vcsUrl.set("https://github.com/Woody230/GradleExtensions.git")

    plugins {
        val id = libs.plugins.woody230.gradle.plugin.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Plugin Publishing"
            description = "Internal publishing for gradle plugins."
            implementationClass = "com.bselzer.gradle.plugin.publish.plugin.PluginPublishPlugin"
        }
    }
}