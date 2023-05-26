package com.bselzer.gradle.internal.android.application.plugin

enum class DefaultProguardFile(internal val fileName: String) {
    UNOPTIMIZED("proguard-android.txt"),
    OPTIMIZED("proguard-android-optimize.txt")
}