package com.bselzer.gradle.internal.composite.property.plugin

import com.bselzer.gradle.function.properties.addOrSkipProperties
import com.bselzer.gradle.function.properties.compositeCompositeProperties
import com.bselzer.gradle.function.properties.compositeLocalPropertiesFileOrNull
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class CompositePropertyPlugin : Plugin<Settings> {
    private val logger: Logger
        get() = Logging.getLogger(Settings::class.java)

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
            includedBuilds.forEach { includedBuild ->
                val outputDir = includedBuild.projectDir.resolve("local.properties").toPath()
                Files.copy(sourceStream, outputDir, StandardCopyOption.REPLACE_EXISTING)
            }
        }
    }
}