plugins {
    `kotlin-dsl`
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.api)
    api(projects.multiplatform)
}