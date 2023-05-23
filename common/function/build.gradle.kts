import com.bselzer.gradle.internal.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
}

jvmPublishExtension {
    description.set("General Gradle extensions.")
}