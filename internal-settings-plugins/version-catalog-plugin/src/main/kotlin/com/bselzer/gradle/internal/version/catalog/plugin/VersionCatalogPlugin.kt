package com.bselzer.gradle.internal.version.catalog.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class VersionCatalogPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("libs") {
                    from(versionsFile)
                }
            }
        }
    }

    private val Settings.versionsFile: File
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
}