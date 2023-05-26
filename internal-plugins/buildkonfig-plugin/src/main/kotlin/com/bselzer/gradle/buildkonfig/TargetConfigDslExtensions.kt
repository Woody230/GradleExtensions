package com.bselzer.gradle.buildkonfig

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.*
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl

fun TargetConfigDsl.boolean(name: String, value: Boolean) = buildConfigField(BOOLEAN, name, value.toString(), nullable = false, const = true)
fun TargetConfigDsl.int(name: String, value: Int) = buildConfigField(INT, name, value.toString(), nullable = false, const = true)
fun TargetConfigDsl.string(name: String, value: String) = buildConfigField(STRING, name, value, nullable = false, const = true)
fun TargetConfigDsl.float(name: String, value: Float) = buildConfigField(FLOAT, name, value.toString(), nullable = false, const = true)
fun TargetConfigDsl.long(name: String, value: Long) = buildConfigField(LONG, name, value.toString(), nullable = false, const = true)