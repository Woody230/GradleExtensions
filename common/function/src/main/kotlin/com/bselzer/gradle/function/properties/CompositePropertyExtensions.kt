package com.bselzer.gradle.function.properties

import com.bselzer.gradle.function.project.compositeBuildSequence
import org.gradle.api.Project
import java.io.File
import java.util.*

val Project.compositeProperties: Properties
    get() = compositePropertiesFileOrNull.load()

val Project.compositePropertiesFileOrNull: File?
    get() = propertiesFileOrNull("composite.properties")

val Project.compositeCompositeProperties: Properties
    get() = compositeCompositePropertiesFileOrNull.load()

val Project.compositeCompositePropertiesFileOrNull: File?
    get() = compositeBuildSequence().firstNotNullOfOrNull { build -> build.rootProject.compositePropertiesFileOrNull }