package com.bselzer.gradle.function.properties

import java.util.*

fun Properties.containsKeys(vararg names: String) = names.all(::containsKey)
