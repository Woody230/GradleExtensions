package io.github.woody230.gradle.convention

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

fun Properties.containsKeys(vararg keys: String) = keys.all(::containsKey)

fun Project.hasProperties(vararg keys: String) = keys.all(::hasProperty)

fun Project.getProperty(key: String): String = properties[key]?.toString() ?: throw Error("Gradle property $key does not exist.")
fun Project.getBooleanPropertyOrFalse(key: String): Boolean = properties[key]?.toString().toBoolean()

fun Project.addOrSkipProperty(key: String, value: String) {
    if (hasProperty(key)) {
        return
    }

    addOrReplaceProperty(key, value)
}

fun Project.addOrSkipProperties(properties: Properties) {
    properties.forEach { pair ->
        val key = pair.key.toString()
        val value = pair.value.toString()
        addOrSkipProperty(key, value)
    }
}

fun Project.addOrReplaceProperty(key: String, value: String) {
    if (hasProperty(key)) {
        setProperty(key, value)
    } else {
        extensions.extraProperties.set(key, value)
    }
}

fun Project.addOrReplaceProperties(properties: Properties) {
    properties.forEach { pair ->
        val key = pair.key.toString()
        val value = pair.value.toString()
        addOrReplaceProperty(key, value)
    }
}

fun Project.injectLocalProperty(localPropertyKey: String, gradlePropertyKey: String) {
    injectLocalProperty(localPropertyKey, gradlePropertyKey) { value -> value }
}

fun Project.injectLocalProperty(localPropertyKey: String, gradlePropertyKey: String, transform: (String) -> String) {
    val localProperties = compositeLocalProperties
    if (!localProperties.containsKey(localPropertyKey)) {
        return
    }

    val value = localProperties.getProperty(localPropertyKey).run(transform)
    addOrReplaceProperty(gradlePropertyKey, value)
}

internal fun Project.propertiesFileOrNull(name: String): File? {
    val file = rootDir.resolve(name)
    return when {
        file.isFile -> file
        else -> null
    }
}

internal fun File?.load(): Properties {
    val properties = Properties()
    if (this != null) {
        InputStreamReader(FileInputStream(this), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }

    return properties
}