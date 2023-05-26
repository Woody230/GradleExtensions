package com.bselzer.gradle.function.properties

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

val Project.localProperties: Properties
    get() {
        val properties = Properties()
        val localProperties = localPropertiesFile

        if (localProperties?.isFile == true) {
            InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                properties.load(reader)
            }
        }
        return properties
    }

private val Project.localPropertiesFile: File?
    get() {
        var path: Path? = rootDir.toPath()
        while (true) {
            if (path == null) {
                return null
            }

            val file = Paths.get(path.toString(), "local.properties").toFile()
            if (!file.exists()) {
                // For composite builds we need to work backwards to the root of the composite.
                path = path.parent
                continue
            }

            return file
        }
    }