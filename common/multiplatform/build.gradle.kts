import com.bselzer.gradle.internal.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
    api(libs.kotlin.plugin)
}

jvmPublishExtension {
    description.set("Kotlin Multiplatform gradle plugin extensions.")
}