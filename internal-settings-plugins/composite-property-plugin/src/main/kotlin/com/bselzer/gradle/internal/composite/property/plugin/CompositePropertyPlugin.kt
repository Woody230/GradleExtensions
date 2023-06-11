package com.bselzer.gradle.internal.composite.property.plugin

import com.bselzer.gradle.function.properties.addOrSkipProperties
import com.bselzer.gradle.function.properties.compositeCompositeProperties
import com.bselzer.gradle.function.properties.compositeLocalPropertiesFileOrNull
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.util.stream.Collectors

class CompositePropertyPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        gradle.projectsLoaded {
            addGradleProperties()
            copyLocalProperties()
        }
    }

    private fun Gradle.addGradleProperties() {
        val properties = rootProject.compositeCompositeProperties

        allprojects {
            // Since these are common properties of the entire composite, do not overwrite if the properties exist already.
            addOrSkipProperties(properties)
        }
    }

    private fun Gradle.copyLocalProperties() {
        val file = rootProject.compositeLocalPropertiesFileOrNull ?: return
        file.inputStream().use { sourceStream ->
            val content = BufferedReader(InputStreamReader(sourceStream)).lines().collect(Collectors.joining("\n"))
            includedBuilds.forEach { includedBuild ->
                val outputDir = includedBuild.projectDir.resolve("local.properties").toPath()
                Files.writeString(outputDir, content)
            }
        }
    }
}