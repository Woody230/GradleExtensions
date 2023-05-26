package io.github.woody230.gradle.convention

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

val Project.localProperties: Properties
    get() {
        val properties = Properties()
        val localProperties = File(rootDir, "local.properties")

        if (localProperties.isFile) {
            InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                properties.load(reader)
            }
        }
        return properties
    }