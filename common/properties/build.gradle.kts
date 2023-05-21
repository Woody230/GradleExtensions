import com.bselzer.gradle.internal.jvm.publish.plugin.jvmPublishExtension

dependencies {
    api(gradleApi())
}

jvmPublishExtension {
    description.set("Gradle property extensions.")
}