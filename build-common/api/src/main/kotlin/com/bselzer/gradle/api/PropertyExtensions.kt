package com.bselzer.gradle.api

import java.util.*

fun Properties.containsKeys(vararg names: String) = names.all(::containsKey)
