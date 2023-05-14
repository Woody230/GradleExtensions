plugins {
    alias(libs.plugins.dsl)
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(projects.multiplatform)
}