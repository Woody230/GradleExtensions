package com.bselzer.gradle.internal.composite.property.plugin

import com.bselzer.gradle.function.properties.addOrReplaceProperties
import com.bselzer.gradle.function.properties.compositeProperties
import com.bselzer.gradle.function.properties.localPropertiesFileOrNull
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
            addOrReplaceGradleProperties()
            copyLocalProperties()
        }
    }

    private fun Gradle.addOrReplaceGradleProperties() {
        val properties = rootProject.compositeProperties
        logger.info("Injecting ${properties.count()} composite properties.")

        allprojects {
            addOrReplaceProperties(properties)
        }
    }

    private fun Gradle.copyLocalProperties() {
        val file = rootProject.localPropertiesFileOrNull
        if (file == null) {
            logger.info("local.properties cannot be copied because the file does not exist")
            return
        }

        file.inputStream().use { sourceStream ->
            includedBuilds.forEach { includedBuild ->
                val outputDir = includedBuild.projectDir.resolve("local.properties").toPath()
                Files.copy(sourceStream, outputDir, StandardCopyOption.REPLACE_EXISTING)
            }
        }
    }
}