import com.bselzer.gradle.internal.maven.publish.plugin.Licensing
import com.bselzer.gradle.internal.plugin.publish.plugin.pluginPublishExtension

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
        classpath(libs.kotlin.plugin)
        classpath(libs.publish.plugin)
        classpath(libs.kotlin.dsl.plugin)
        classpath(libs.woody230.gradle.function)
        classpath(libs.woody230.gradle.internal.maven.publish.plugin)
        classpath(libs.woody230.gradle.multiplatform)
        classpath(libs.woody230.gradle.internal.plugin.publish.plugin)
    }
}

// TODO must use root project: extension libs does not exist https://github.com/gradle/gradle/issues/18237
subprojects {
    apply(plugin = rootProject.libs.plugins.dsl.get().pluginId)
    apply(plugin = rootProject.libs.plugins.jvm.get().pluginId)

    pluginPublishExtension {
        coordinates.category.set("gradle.internal")
        version.set(rootProject.libs.versions.woody230.gradle)
        repository.set("https://github.com/Woody230/GradleExtensions")
        licensing.set(Licensing.APACHE_2_0)

        tags.add("internal")
    }

    afterEvaluate {
        apply(plugin = rootProject.libs.plugins.woody230.gradle.internal.plugin.publish.get().pluginId)
    }
}

tasks.register("publishBuildToMavenLocal") {
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}