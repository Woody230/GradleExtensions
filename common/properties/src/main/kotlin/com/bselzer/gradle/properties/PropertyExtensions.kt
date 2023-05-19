package com.bselzer.gradle.properties

import org.gradle.api.Project
import java.util.*

fun Properties.containsKeys(vararg keys: String) = keys.all(::containsKey)

fun Project.hasProperties(vararg keys: String) = keys.all(::hasProperty)