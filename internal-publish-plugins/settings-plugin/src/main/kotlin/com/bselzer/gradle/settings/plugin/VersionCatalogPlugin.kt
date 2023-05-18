package com.bselzer.gradle.settings.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import java.io.File
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
            var path = Paths.get(rootDir.path)
            while (true) {
                val file = Paths.get(path.toString(), RELATIVE_PATH).toFile()
                if (file.exists()) {
                    return file
                }

                // For composite builds we need to work backwards to the root of the composite.
                path = path.parent
            }
        }
}