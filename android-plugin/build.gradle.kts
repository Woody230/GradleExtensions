plugins {
    alias(libs.plugins.dsl)
    alias(libs.plugins.jvm)
}

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}