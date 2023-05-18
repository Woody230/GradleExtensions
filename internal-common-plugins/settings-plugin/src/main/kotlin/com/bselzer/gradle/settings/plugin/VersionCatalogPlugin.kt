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
        val toml = toml ?: return@with
        dependencyResolutionManagement {
            versionCatalogs {
                create("libs") {
                    from(toml)
                }
            }
        }
    }

    private val Settings.toml: File?
        get() {
            var path: Path? = rootDir.toPath()
            while (true) {
                if (path == null) {
                    throw FileNotFoundException("Expected to find the version catalog at $RELATIVE_PATH in the project root directory $rootDir or higher level directory but it does not exist.")
                }

                val file = Paths.get(path.toString(), RELATIVE_PATH).toFile()
                if (!file.exists()) {
                    // For composite builds we need to work backwards to the root of the composite.
                    path = path.parent
                    continue
                }

                if (path == rootDir.toPath()) {
                    // If applied from the root of the composite, then we should not add the file because it will be done by default.
                    // https://github.com/gradle/gradle/issues/21328
                    // https://github.com/gradle/gradle/issues/20282
                    // https://github.com/gradle/gradle/blob/393a2ea92a43f04b75c367f0759266be9eff46ae/subprojects/core/src/main/java/org/gradle/configuration/BuildTreePreparingProjectsPreparer.java#L77-L86
                    return null
                }

                return file
            }
        }
}