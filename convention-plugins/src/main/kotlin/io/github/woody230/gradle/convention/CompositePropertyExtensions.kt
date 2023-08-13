package io.github.woody230.gradle.convention

import org.gradle.api.Project
import java.io.File
import java.util.*

/**
 * The composite.properties from the root directory.
 */
val Project.compositeProperties: Properties
    get() = compositePropertiesFileOrNull.load()

/**
 * The composite.properties file from the root directory.
 */
val Project.compositePropertiesFileOrNull: File?
    get() = propertiesFileOrNull("composite.properties")

/**
 * The composite.properties from the root directory of this project or from a parent build.
 */
val Project.compositeCompositeProperties: Properties
    get() = compositeCompositePropertiesFileOrNull.load()

/**
 * The composite.properties file from the root directory of this project or from a parent build.
 */
val Project.compositeCompositePropertiesFileOrNull: File?
    get() = gradle.compositeSequence().firstNotNullOfOrNull { gradle -> gradle.rootProject.compositePropertiesFileOrNull }