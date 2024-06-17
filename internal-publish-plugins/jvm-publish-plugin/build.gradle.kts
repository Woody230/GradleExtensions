plugins {
    id(libs.plugins.woody230.gradle.convention.plugin.get().pluginId)
}

dependencies {
    api(libs.dokka.plugin)
    api(projects.mavenPublishPlugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
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