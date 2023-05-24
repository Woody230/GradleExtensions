plugins {
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.android.plugin)
}

mavenPublishing {
    pom {
        description.set("Android gradle plugin extensions.")
    }
}