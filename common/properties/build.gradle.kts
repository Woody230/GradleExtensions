import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension

plugins {
    `kotlin-dsl`
}

dependencies {
    api(gradleApi())
}

jvmPublishExtension {
    description.set("Gradle property extensions.")
}