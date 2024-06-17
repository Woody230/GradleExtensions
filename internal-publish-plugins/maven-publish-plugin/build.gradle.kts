plugins {
    id(libs.plugins.woody230.gradle.convention.plugin.get().pluginId)
}

dependencies {
    api(libs.kotlin.plugin)
    api(libs.vanniktech.publish.plugin)
    api(libs.woody230.gradle.function)
    api(libs.woody230.gradle.internal.models)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

mavenPublishing {
    pom {
        description.set("Internal publishing to Maven Central.")
    }
}