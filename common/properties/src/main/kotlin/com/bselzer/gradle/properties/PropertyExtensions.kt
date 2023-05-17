package com.bselzer.gradle.properties

import java.util.*

fun Properties.containsKeys(vararg names: String) = names.all(::containsKey)
