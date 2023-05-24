plugins {
    id(libs.plugins.woody230.convention.publish.get().pluginId)
}

dependencies {
    api(gradleApi())
    api(libs.android.plugin)

    // TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

mavenPublishing {
    pom {
        description.set("Android gradle plugin extensions.")
    }
}