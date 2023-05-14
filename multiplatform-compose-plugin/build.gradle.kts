plugins {
    alias(libs.plugins.dsl)
    alias(libs.plugins.jvm)
}

dependencies {
    api(projects.androidPlugin)
    api(libs.compose.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}