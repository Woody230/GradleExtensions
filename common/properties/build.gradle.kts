import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension
import com.bselzer.gradle.maven.publish.plugin.Licensing

plugins {
    `kotlin-dsl`
}

dependencies {
    api(gradleApi())
}

jvmPublishExtension {
    subGroupId.set("gradle")
    version.set(rootProject.version.toString())
    repository.set("https://github.com/Woody230/GradleExtensions")
    description.set("Gradle property extensions.")
    licensing.set(Licensing.APACHE_2_0)
}

apply(plugin = libs.plugins.woody230.gradle.jvm.publish.get().pluginId)