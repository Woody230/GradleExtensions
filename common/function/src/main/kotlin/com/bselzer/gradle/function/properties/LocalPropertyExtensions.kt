package com.bselzer.gradle.function.properties

import com.bselzer.gradle.function.project.compositeBuildSequence
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

val Project.compositeLocalProperties: Properties
    get() = compositeLocalPropertiesFile.load()

private val Project.compositeLocalPropertiesFile: File?
    get() = compositeBuildSequence().firstNotNullOfOrNull { build -> build.rootProject.localPropertiesFile }

val Project.localProperties: Properties
    get() = localPropertiesFile.load()

val Project.localPropertiesFile: File?
    get() {
        val file = rootDir.resolve("local.properties")
        return when {
            file.isFile -> file
            else -> null
        }
    }

private fun File?.load(): Properties {
    val properties = Properties()
    if (this != null) {
        InputStreamReader(FileInputStream(this), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }

    return properties
}