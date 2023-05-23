package com.bselzer.gradle.function.properties

import org.gradle.api.Project
import java.util.*

fun Properties.containsKeys(vararg keys: String) = keys.all(::containsKey)

fun Project.hasProperties(vararg keys: String) = keys.all(::hasProperty)

fun Project.getProperty(key: String): String = properties[key]?.toString() ?: throw Error("Gradle property $key does not exist.")