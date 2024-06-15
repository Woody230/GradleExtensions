plugins {
    id(libs.plugins.woody230.gradle.convention.jvm.get().pluginId)
}

dependencies {
    implementation(libs.woody230.gradle.function)
}

mavenPublishing {
    pom {
        description.set("Base plugin for adding composite build tasks.")
    }
}