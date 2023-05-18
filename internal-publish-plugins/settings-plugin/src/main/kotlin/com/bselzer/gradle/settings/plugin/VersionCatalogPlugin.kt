package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import java.nio.file.Paths

class VersionCatalogPlugin : Plugin<Settings> {
    private companion object {
        const val RELATIVE_PATH = "gradle/libs.versions.toml"
    }

    override fun apply(settings: Settings) = with(settings) {
        dependencyResolutionManagement {
            versionCatalogs {
                create("libs") {
                    from(toml)
                }
            }
        }
    }

    private val Settings.toml: File
        get() {
            var path: Path? = Paths.get(rootDir.path)
            while (true) {
                if (path == null) {
                    throw FileNotFoundException("Expected to find the version catalog at $RELATIVE_PATH in the project root directory $rootDir or higher level directory but it does not exist.")
                }

                val file = Paths.get(path.toString(), RELATIVE_PATH).toFile()
                if (file.exists()) {
                    return file
                }

                // For composite builds we need to work backwards to the root of the composite.
                path = path.parent
            }
        }
}