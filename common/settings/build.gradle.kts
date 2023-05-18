import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
}

jvmPublishExtension {
    description.set("Gradle settings extensions.")
}