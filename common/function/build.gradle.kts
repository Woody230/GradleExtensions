plugins {
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(gradleApi())
}

mavenPublishing {
    pom {
        description.set("General Gradle extensions.")
    }
}