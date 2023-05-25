package com.bselzer.gradle.android.configure.component

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.bselzer.gradle.android.libraryAndroidComponentsExtension
import com.bselzer.gradle.android.libraryAndroidComponentsExtensionOrNull
import org.gradle.api.Project

class LibraryAndroidComponents internal constructor(
    project: Project
) : AndroidComponentsConfigurer<LibraryAndroidComponentsExtension, LibraryExtension> by InternalAndroidComponentsConfigurer(
    project,
    { libraryAndroidComponentsExtension },
    { libraryAndroidComponentsExtensionOrNull }
)

val Project.libraryAndroidComponents: LibraryAndroidComponents
    get() = LibraryAndroidComponents(this)

fun Project.libraryAndroidComponents(configure: LibraryAndroidComponents.() -> Unit) = libraryAndroidComponents.configure()