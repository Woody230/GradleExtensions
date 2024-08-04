package com.bselzer.gradle.function.settings

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.GradleInternal
import org.gradle.api.internal.file.DefaultFileOperations
import org.gradle.api.internal.file.FileCollectionFactory
import org.gradle.api.internal.file.FileLookup
import org.gradle.api.internal.file.FileOperations
import java.io.File

val Settings.fileOperations: FileOperations
    get() {
        // https://github.com/gradle/gradle/blob/c21d250a99b54f31e20d90a20a9ffe067b208838/subprojects/kotlin-dsl/src/main/kotlin/org/gradle/kotlin/dsl/support/KotlinScriptHost.kt#L110
        val serviceRegistry = (gradle as GradleInternal).services
        val fileLookup = serviceRegistry.get(FileLookup::class.java)
        val fileResolver = fileLookup.getFileResolver(rootDir)
        val fileCollectionFactory = serviceRegistry.get(FileCollectionFactory::class.java).withResolver(fileResolver)
        return DefaultFileOperations.createSimple(fileResolver, fileCollectionFactory, serviceRegistry)
    }

fun Settings.files(vararg paths: String): ConfigurableFileCollection = fileOperations.configurableFiles(paths)

fun Settings.files(vararg files: File): ConfigurableFileCollection = fileOperations.configurableFiles(files)