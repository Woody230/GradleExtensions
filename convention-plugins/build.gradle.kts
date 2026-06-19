plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.plugin)
    implementation(libs.publish.plugin)
    implementation(libs.dokka.plugin)
    implementation(libs.vanniktech.publish.plugin)
}

tasks.register("publishRecursivelyToMavenCentral") {
    // Nothing needs to be published.
}

tasks.register("publishRecursivelyToMavenLocal") {
    // Nothing needs to be published.
}