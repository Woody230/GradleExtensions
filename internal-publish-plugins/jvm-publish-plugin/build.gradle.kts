plugins {
    id(libs.plugins.java.get().pluginId)
    id(libs.plugins.gradle.publish.get().pluginId)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(libs.woody230.gradle.properties)
    api(libs.vanniktech.publish.gradle.plugin)
    api(libs.dokka.gradle.plugin)
    api(libs.publish.gradle.plugin)
    implementation(projects.mavenPublishPlugin)
}

gradlePlugin {
    website.set("https://github.com/Woody230/GradleExtensions")
    vcsUrl.set("https://github.com/Woody230/GradleExtensions.git")

    plugins {
        val id = libs.plugins.woody230.gradle.jvm.publish.get().pluginId
        create(id) {
            this.id = id
            displayName = "Kotlin JVM Project Publishing"
            description = "Internal publishing for Kotlin JVM projects."
            implementationClass = "com.bselzer.gradle.jvm.publish.plugin.JvmPublishPlugin"
        }
    }
}