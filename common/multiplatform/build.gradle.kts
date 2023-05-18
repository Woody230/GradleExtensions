import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
    api(libs.kotlin.gradle.plugin)
    api(libs.kotlin.multiplatform.gradle.plugin)
}

jvmPublishExtension {
    description.set("Kotlin Multiplatform gradle plugin extensions.")
}