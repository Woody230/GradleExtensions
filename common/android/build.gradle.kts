import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
    api(libs.android.plugin)

    // TODO can't access libs from precompiled scripts https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

jvmPublishExtension {
    description.set("Android gradle plugin extensions.")
}