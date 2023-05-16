import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.gradle.kotlin.dsl.libs
import org.gradle.kotlin.dsl.projects

plugins {
    id(libs.plugins.java.get().pluginId)
    id(libs.plugins.gradle.publish.get().pluginId)
    `kotlin-dsl`
    id(libs.plugins.jvm.get().pluginId)
}

dependencies {
    api(libs.woody230.gradle.api)
    api(libs.kotlin.gradle.plugin)
    api(libs.dokka.gradle.plugin)
    implementation(libs.woody230.gradle.maven.publish)
}