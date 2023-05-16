plugins {
    id(libs.plugins.java.get().pluginId)
    id(libs.plugins.gradle.publish.get().pluginId)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.android.gradle.plugin)

    // TODO can't access libs https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}