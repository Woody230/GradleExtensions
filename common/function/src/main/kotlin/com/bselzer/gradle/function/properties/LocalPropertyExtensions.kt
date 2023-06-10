package com.bselzer.gradle.function.properties

import com.bselzer.gradle.function.project.compositeBuildSequence
import org.gradle.api.Project
import java.io.File
import java.util.*

/**
 * The local.properties from the root directory.
 */
val Project.localProperties: Properties
    get() = localPropertiesFileOrNull.load()

/**
 * The local.properties file from the root directory.
 */
val Project.localPropertiesFileOrNull: File?
    get() = propertiesFileOrNull("local.properties")

/**
 * The local.properties from the root directory of this project or from a parent build.
 */
val Project.compositeLocalProperties: Properties
    get() = compositeLocalPropertiesFileOrNull.load()

/**
 * The local.properties file from the root directory of this project or from a parent build.
 */
val Project.compositeLocalPropertiesFileOrNull: File?
    get() = compositeBuildSequence().firstNotNullOfOrNull { build -> build.rootProject.localPropertiesFileOrNull }
