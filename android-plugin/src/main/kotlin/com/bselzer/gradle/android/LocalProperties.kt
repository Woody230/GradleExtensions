package com.bselzer.gradle.android

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import java.util.*

class LocalProperties(
    private val project: Project
)
{
    private val properties: Properties = gradleLocalProperties(project.rootDir)

    fun get(name: String): String = getOrNull(name) ?: throw missingProperty(name)
    fun getOrNull(name: String): String? = properties.getProperty(name)
    fun getOrEmpty(name: String): String = getOrNull(name) ?: ""

    fun exist(vararg names: String): Boolean = names.all(::exist)
    fun exist(name: String): Boolean = getOrNull(name) != null

    fun readTextOrNull(name: String): String?
    {
        val path = getOrNull(name)
        return path?.let { project.file(path).readText() }
    }

    fun readText(name: String): String = readTextOrNull(name) ?: throw missingProperty(name)

    private fun missingProperty(name: String): Exception = throw NotImplementedError("Local property $name is not set.")
}