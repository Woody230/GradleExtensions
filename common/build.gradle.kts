import com.bselzer.gradle.jvm.publish.plugin.jvmPublishExtension
import com.bselzer.gradle.maven.publish.plugin.Licensing

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.woody230.gradle.jvm.publish)
    }
}

// TODO must use root project: extension libs does not exist https://github.com/gradle/gradle/issues/18237
subprojects {
    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)

    jvmPublishExtension {
        subGroupId.set("gradle")
        version.set("1.0.0")
        repository.set("https://github.com/Woody230/GradleExtensions")
        licensing.set(Licensing.APACHE_2_0)
    }

    apply(plugin = rootProject.libs.plugins.woody230.gradle.jvm.publish.get().pluginId)
}