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

    // TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks.register("publishRecursivelyToMavenCentral") {
    // Nothing needs to be published.
}

tasks.register("publishRecursivelyToMavenLocal") {
    // Nothing needs to be published.
}