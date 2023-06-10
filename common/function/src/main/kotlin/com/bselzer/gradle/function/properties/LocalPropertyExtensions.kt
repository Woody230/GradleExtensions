package com.bselzer.gradle.function.properties

import com.bselzer.gradle.function.project.compositeBuildSequence
import org.gradle.api.Project
import java.io.File
import java.util.*

val Project.compositeLocalProperties: Properties
    get() = compositeLocalPropertiesFileOrNull.load()

val Project.compositeLocalPropertiesFileOrNull: File?
    get() = compositeBuildSequence().firstNotNullOfOrNull { build -> build.rootProject.localPropertiesFileOrNull }

val Project.localProperties: Properties
    get() = localPropertiesFileOrNull.load()

val Project.localPropertiesFileOrNull: File?
    get() = propertiesFileOrNull("local.properties")

