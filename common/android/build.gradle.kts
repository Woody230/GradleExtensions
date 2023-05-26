plugins {
    id(libs.plugins.woody230.gradle.convention.jvm.get().pluginId)
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