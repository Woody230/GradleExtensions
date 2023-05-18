package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File
import java.io.FileNotFoundException
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
            var file: File? = Paths.get(rootDir.path, RELATIVE_PATH).toFile()
            while (true) {
                if (file == null) {
                    throw FileNotFoundException("Expected to find the version catalog at $RELATIVE_PATH in the project root directory $rootDir or higher level directory but it does not exist.")
                }

                if (file.exists()) {
                    return file
                }

                // For composite builds we need to work backwards to the root of the composite.
                file = file.parentFile
            }
        }
}