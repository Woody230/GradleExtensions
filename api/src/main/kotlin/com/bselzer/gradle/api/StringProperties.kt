package com.bselzer.gradle.api

import java.util.*

class StringProperties(
    private val properties: Properties
) : Map<String, String> by properties.toStringMap() {

    private companion object {
        fun Properties.toStringMap(): Map<String, String> = toMap().mapKeys { entry -> entry.key.toString() }.mapValues { entry -> getProperty(entry.key) }
    }
}