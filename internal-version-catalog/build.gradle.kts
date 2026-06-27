import com.bselzer.gradle.internal.maven.publish.plugin.Licensing
import com.bselzer.gradle.internal.version.catalog.publish.plugin.versionCatalogPublishExtension

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
        classpath(libs.woody230.gradle.internal.version.catalog.publish.plugin)
    }
}

// NOTE must use root project: extension libs does not exist https://github.com/gradle/gradle/issues/18237
subprojects {
    // TODO Extension of type 'VersionCatalogsExtension' does not exist https://github.com/gradle/gradle/issues/31289
    afterEvaluate {
        apply(plugin = rootProject.libs.plugins.woody230.gradle.internal.version.catalog.publish.get().pluginId)
    }

    versionCatalogPublishExtension {
        coordinates.category.set("gradle.internal")
        version.set(rootProject.libs.versions.woody230.gradle)
        repository.set("https://github.com/Woody230/GradleExtensions")
        licensing.set(Licensing.APACHE_2_0)
    }
}

tasks.register("publishRecursivelyToMavenCentral") {
    group = "composite"
    val tasks = getTasksByName("publishAllPublicationsToMavenCentralRepository", true)
    dependsOn(tasks)
}

tasks.register("publishRecursivelyToMavenLocal") {
    group = "composite"
    val tasks = getTasksByName("publishToMavenLocal", true)
    dependsOn(tasks)
}