plugins {
    id(libs.plugins.woody230.gradle.convention.jvm.get().pluginId)
}

dependencies {
    api(gradleApi())
}

mavenPublishing {
    pom {
        description.set("General Gradle extensions.")
    }
}