package com.bselzer.gradle.function.properties

import org.gradle.api.Project
import java.io.File
import java.util.*

val Project.compositeProperties: Properties
    get() = compositePropertiesFileOrNull.load()

val Project.compositePropertiesFileOrNull: File?
    get() = propertiesFileOrNull("composite.properties")