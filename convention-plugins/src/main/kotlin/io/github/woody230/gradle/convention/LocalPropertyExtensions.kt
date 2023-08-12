package io.github.woody230.gradle.convention

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

val Project.localProperties: Properties
    get() {
        val properties = Properties()
        localPropertiesFile?.let { localProperties ->
            InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                properties.load(reader)
            }
        }

        return properties
    }

private val Project.localPropertiesFile: File?
    get() = compositeBuildSequence().firstNotNullOfOrNull { build ->
        val file = build.rootProject.rootDir.resolve("local.properties")
        when {
            file.isFile -> file
            else -> null
        }
    }