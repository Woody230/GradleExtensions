package com.bselzer.gradle.internal.composite.property.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class CompositePropertyPlugin : Plugin<Settings> {
    private val logger: Logger
        get() = Logging.getLogger(Settings::class.java)

    override fun apply(settings: Settings) = with(settings) {
        gradle.projectsLoaded {
            copyLocalProperties()
        }
    }

    private fun Gradle.copyLocalProperties() {
        val file = localPropertiesFile
        if (file == null) {
            logger.info("local.properties cannot be copied because the file does not exist in the root directory")
            return
        }

        file.inputStream().use { sourceStream ->
            includedBuilds.forEach { includedBuild ->
                val outputDir = includedBuild.projectDir.resolve("local.properties").toPath()
                Files.copy(sourceStream, outputDir, StandardCopyOption.REPLACE_EXISTING)
            }
        }
    }

    private val Gradle.localPropertiesFile: File?
        get() {
            val file = rootProject.projectDir.resolve("local.properties")
            return when {
                file.isFile -> file
                else -> null
            }
        }
}