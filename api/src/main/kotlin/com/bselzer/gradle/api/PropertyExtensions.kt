package com.bselzer.gradle.api

import java.util.Properties

fun Properties.containsKeys(vararg names: String) = names.all(::containsKey)
