import com.bselzer.gradle.plugin.publish.plugin.pluginPublishExtension

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
        classpath(libs.publish.gradle.plugin)
        classpath(libs.woody230.gradle.properties)
        classpath(libs.woody230.gradle.maven.publish)
        classpath(libs.woody230.gradle.multiplatform)
        classpath(libs.woody230.gradle.plugin.publish)
    }
}

// TODO must use root project: extension libs does not exist https://github.com/gradle/gradle/issues/18237
subprojects {
    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)

    pluginPublishExtension {
        subGroupId.set("gradle")
        version.set("1.0.0")
        repository.set("https://github.com/Woody230/GradleExtensions")
    }

    afterEvaluate {
        apply(plugin = rootProject.libs.plugins.woody230.gradle.plugin.publish.get().pluginId)
    }
}