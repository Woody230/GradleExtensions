package com.bselzer.gradle.internal.version.catalog.plugin

import com.bselzer.gradle.function.settings.files
import org.gradle.api.initialization.Settings
import org.gradle.api.logging.Logger
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

internal fun Settings.createVersionCatalog(logger: Logger, name: String, file: File) {
    // NOTE the gradle/libs.versions.toml within the root directory is automatically included and must not be added again
    // https://github.com/gradle/gradle/issues/20282
    if (file.startsWith(rootDir)) {
        return
    }

    dependencyResolutionManagement {
        versionCatalogs {
            if (names.contains(name)) {
                logger.info("Skipping creation of the $name version catalog because it already exists.")
                return@versionCatalogs
            }

            create(name) {
                logger.info("Creating the $name version catalog from ${file.path}")
                from(files(file))
            }
        }
    }
}

internal val Settings.versionsFile: File
    get() {
        // TODO ability to access what build directories have been included instead of going all the way to the root of the drive?
        var path: Path? = rootDir.toPath()
        while (true) {
            if (path == null) {
                throw Error("Unable to find the libs.versions.toml file within the gradle folder of the current directory or a parent directory.")
            }

            val file = Paths.get(path.toString(), "gradle", "libs.versions.toml").toFile()
            if (!file.exists()) {
                // For composite builds we need to work backwards toward the root of the composite.
                path = path.parent
                continue
            }

            return file
        }
    }